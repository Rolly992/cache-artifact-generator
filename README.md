# cache-artifact-generator

A Gradle project to generate a large number of big build cache artifacts

## Usage

Control the behavior by modifying `build.gradle.kts`:

- Change the number of artifacts to generate by changing the `numberOfEntries` variable.
- Change the size of each entry by changing the `entrySizeMb` variable.

Generate cache entries by running `./gradlew :generate`
