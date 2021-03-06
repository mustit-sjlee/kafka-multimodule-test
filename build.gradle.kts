
plugins {
  java
}


allprojects {
  group = "org.example"
  version = "1.0-SNAPSHOT"
  repositories {
    mavenCentral()
  }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    subprojects.forEach{
      archives(it)
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}