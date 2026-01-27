plugins {
	id("org.jetbrains.kotlin.jvm") version "2.2.21"
	id("org.jetbrains.kotlin.plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.jetbrains.kotlin.plugin.jpa") version "2.2.21"
}

group = "br.com.fiap.adj8.phase5"
version = "0.0.1-SNAPSHOT"
description = "prioritas"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(24))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// ---------------------------------------------------------
	// 1. Core & Web
	// ---------------------------------------------------------
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// ---------------------------------------------------------
	// 2. Database
	// ---------------------------------------------------------
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")

	// ---------------------------------------------------------
	// 3. Documentation (OpenAPI / Swagger)
	// ---------------------------------------------------------
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	// ---------------------------------------------------------
	// 4. Testing (Unit & Integration)
	// ---------------------------------------------------------
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Mockk
	testImplementation("io.mockk:mockk:1.13.9")
	testImplementation("com.ninja-squad:springmockk:4.0.2")

	// ---------------------------------------------------------
	// 5. Testcontainers
	// ---------------------------------------------------------
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter:1.19.7")
	testImplementation("org.testcontainers:postgresql:1.19.7")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}