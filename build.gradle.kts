plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "internship.java"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.6")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.data:spring-data-jpa:3.3.0")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.3.0")
	implementation("com.h2database:h2")

	compileOnly ("org.projectlombok:lombok:1.18.32")
	annotationProcessor ("org.projectlombok:lombok:1.18.32")

	testCompileOnly ("org.projectlombok:lombok:1.18.32")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.32")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
