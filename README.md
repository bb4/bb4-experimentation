# bb4-experimentation

📊 [Build status for all bb4 projects](https://github.com/bb4/.github)

Some experimental projects built using the bb4 framework. Some are just quick prototypes or tests, while others may be split out into a separate repository when mature enough.

**Build conventions:** this repo uses [bb4-gradle](https://github.com/barrybecker4/bb4-gradle) **2.1-SNAPSHOT** via the Gradle `plugins { }` DSL (Java **25**, Gradle **9.8.2**). `settings.gradle` adds **`https://central.sonatype.com/repository/maven-snapshots/`** to `pluginManagement` so Gradle can resolve that SNAPSHOT (OSSRH is EOL). Optionally, for local plugin work without publishing, use `mavenLocal()` there and run `./gradlew publishToMavenLocal` in bb4-gradle.
