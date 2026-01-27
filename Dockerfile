# -----------------------------------------------------------------------------
# Stage 1: Builder
# Use the JDK directly (not the Gradle image) and use the Wrapper
# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /build

# 1. Copy the Wrapper scripts and configuration first
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# 2. Grant execution permissions to the wrapper
RUN chmod +x gradlew

# 3. Download dependencies (this layer will be cached unless dependencies change)
RUN ./gradlew dependencies --no-daemon

# 4. Copy the source code
COPY src ./src

# 5. Build the JAR using the wrapper
RUN ./gradlew bootJar --no-daemon -x test

# -----------------------------------------------------------------------------
# Stage 2: Extractor
# (No changes needed here, but included for completeness)
# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre AS extractor
WORKDIR /build
COPY --from=builder /build/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# -----------------------------------------------------------------------------
# Stage 3: Runtime
# (No changes needed here)
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