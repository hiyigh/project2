import java.util.*

plugins {
    id("java")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "project2"
version = "1.0-SNAPSHOT" +

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-json") //3.2.2
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

    implementation ("org.springframework.boot:spring-boot-starter-jdbc")
    implementation ("org.mariadb.jdbc:mariadb-java-client")
    implementation ("com.zaxxer:HikariCP") //5.0.1
    implementation ("org.springframework.boot:spring-boot-starter-aop")

    compileOnly ("org.projectlombok:lombok")

    implementation("com.github.iamport:iamport-rest-client-java:0.2.21")




    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.3.72")
    testImplementation(group = "org.mockito.kotlin", name = "mockito-kotlin", version = "4.0.0")
}

tasks.test {
    useJUnitPlatform()
}