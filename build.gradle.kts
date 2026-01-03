plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.security:spring-security-crypto:6.2.0")
//    implementation("org.springframework.ai:spring-ai-starter-mcp-server")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // jBCrypt - lightweight, no Spring dependencies
    implementation("org.mindrot:jbcrypt:0.4")
    // Modern BCrypt implementation with additional features
    implementation("at.favre.lib:bcrypt:0.10.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
