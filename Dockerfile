# -----------------------------------------------------------------------------
# Stage 1: Builder
# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /build

# 1. Copia o Wrapper e configurações da RAIZ do projeto
# O contexto do docker-compose é a raiz (.), então ele vê tudo
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# 2. Copia o módulo 'common' (Dependência obrigatória)
COPY common common

# 3. Copia o código do APP (Agora com o nome correto 'prioritas')
COPY prioritas prioritas

RUN mkdir notification-app

# 4. Dá permissão de execução ao wrapper
RUN chmod +x gradlew

# 5. Executa o build ESPECÍFICO para o projeto ':prioritas'
# Isso vai compilar o 'common' automaticamente e depois o 'prioritas'
RUN ./gradlew :prioritas:bootJar --no-daemon -x test

# -----------------------------------------------------------------------------
# Stage 2: Extractor
# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre AS extractor
WORKDIR /build

# AJUSTE NO CAMINHO: O jar agora é gerado dentro de prioritas/build/libs
COPY --from=builder /build/prioritas/build/libs/*.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract

# -----------------------------------------------------------------------------
# Stage 3: Runtime
# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre
WORKDIR /app

RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

COPY --from=extractor /build/dependencies/ ./
COPY --from=extractor /build/spring-boot-loader/ ./
COPY --from=extractor /build/snapshot-dependencies/ ./
COPY --from=extractor /build/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]