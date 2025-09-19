plugins {
    id("java")
    id("com.diffplug.spotless") version "6.25.0"
}

group = "io.codehunters.ans"
version = "1.0.0"

repositories {
    mavenCentral()
}

// Configuration for PlantUML rendering
configurations {
    create("plantuml")
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.7")
    // Micrometer core for metrics
    implementation("io.micrometer:micrometer-core:1.13.1")

    // Spring core pieces used by the aspect and SpEL
    implementation("org.springframework:spring-context:6.1.12")
    implementation("org.springframework:spring-aop:6.1.12")
    implementation("org.springframework:spring-expression:6.1.12")

    // AspectJ annotations and (optional) weaver
    implementation("org.aspectj:aspectjrt:1.9.22")
    runtimeOnly("org.aspectj:aspectjweaver:1.9.22")

    // PlantUML CLI
    add("plantuml", "net.sourceforge.plantuml:plantuml:1.2024.7")

    // Tests
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework:spring-test:6.1.12")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

spotless {
    java {
        target("src/**/*.java")
        palantirJavaFormat()
        // Optional: trim and remove unused imports
        trimTrailingWhitespace()
        endWithNewline()
        removeUnusedImports()
    }
}

// Directories for UML sources and outputs
val plantUmlInputDir = layout.projectDirectory.dir("src/uml")
val plantUmlOutputDir = layout.buildDirectory.dir("diagrams")

// Generate a class diagram from PlantUML sources (SVG by default)
tasks.register<JavaExec>("generateClassDiagram") {
    group = "documentation"
    description = "Generates UML diagrams from PlantUML sources in src/uml to build/diagrams"
    classpath = configurations.named("plantuml").get()
    mainClass.set("net.sourceforge.plantuml.Run")
    // Ensure output directory exists
    doFirst {
        plantUmlOutputDir.get().asFile.mkdirs()
    }
    // Render all .puml files under src/uml to SVG into build/diagrams
    args("-tsvg", "-o", plantUmlOutputDir.get().asFile.absolutePath, plantUmlInputDir.asFile.absolutePath)
}

// Optional: PNG output
tasks.register<JavaExec>("generateClassDiagramPng") {
    group = "documentation"
    description = "Generates UML diagrams (PNG) from PlantUML sources in src/uml to build/diagrams"
    classpath = configurations.named("plantuml").get()
    mainClass.set("net.sourceforge.plantuml.Run")
    doFirst {
        plantUmlOutputDir.get().asFile.mkdirs()
    }
    args("-tpng", "-o", plantUmlOutputDir.get().asFile.absolutePath, plantUmlInputDir.asFile.absolutePath)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}