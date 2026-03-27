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
    implementation(platform("software.amazon.awssdk:bom:2.27.21"))
    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk:bedrock")
    implementation("software.amazon.awssdk:bedrockruntime")
    implementation("software.amazon.awssdk:dynamodb")
    implementation("software.amazon.awssdk:dynamodb-enhanced")
    implementation("software.amazon.awssdk:ecs")
    implementation("software.amazon.awssdk:sts")
    implementation("software.amazon.awssdk:sqs")
    implementation("software.amazon.awssdk:sns")
    implementation("software.amazon.awssdk:ec2")
    implementation("software.amazon.awssdk:cloudwatch")
    implementation("software.amazon.awssdk:kms")
    implementation("software.amazon.awssdk:iam")
    implementation("software.amazon.awssdk:regions")
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
