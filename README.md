# Interview Notes Code Repository

A curated collection of Java coding solutions, algorithms, and reference implementations created from real interview
practice. This repository is structured as a Maven project with Spring Boot support, and contains categorized solutions
by year/month/assessment as well as domain-focused examples (e.g., Amazon-style problems, data structures, concurrency,
web, etc.).

## Table of Contents

- Overview and Goals
- Tech Stack
- Project Layout
- Quick Start
- Build and Run
- Testing
- Coding Guidelines and Conventions
- Notable Modules and Examples
- Troubleshooting
- How to Contribute
- FAQ
- License

## Overview and Goals

This project serves as a living knowledge base of interview-oriented code:

- Ready-to-run Java programs for algorithmic problems and assessments.
- Clean, well-documented approaches that highlight edge cases, correctness, and performance.
- Reusable utilities for HTTP, parsing, collections, and testing.
- Spring Boot app scaffolding for web APIs and experimentation.

## Tech Stack

- Java 17
- Maven + Spring Boot (2.x line)
- JUnit 4 and Spring Boot Test
- Popular libraries used where relevant (Gson, Apache Commons, Kafka clients, Redis Jedis, WebFlux)

Key dependencies are defined in pom.xml.

## Quick Start

1) Clone and enter the project directory:
   git clone <your-fork-or-clone-url>
   cd interview-notes-code
2) Ensure prerequisites:
    - Java 17 (java -version)
    - Maven 3.8+ (mvn -version)
3) Build and run tests:
   mvn clean install
4) Run a sample class (e.g., Solution.java):
   mvn -Dexec.mainClass=com.interview.notes.code.year.y2025.september.assesment.test6.Solution \
   -Dexec.classpathScope=runtime \
   org.codehaus.mojo:exec-maven-plugin:3.1.0:java

Tip: You can also run any class with a main method directly from your IDE.

## Project Layout

Root-level key files/directories:

- pom.xml: Maven configuration
- src/main/java: Source code
- src/test/java: Unit and integration tests
- src/main/resources: App resources (static, templates)
- README.md: This document
- HELP.md: Maven/Spring getting started references

Within src/main/java the code is organized by packages indicating year/month/assessment and topic areas. Examples:

- com.interview.notes.code.year.y2025.september.assesment.test6
    - Solution.java: An end-to-end solution fetching and processing paginated API data, including robust filtering and
      tie-breaking rules, with a main method for quick manual tests.
    - BooleanCheck.java, ConsoleOutputCheck.java: Utility checks used in assessments.
- com.interview.notes.code.amazon
    - NumberofAirplanesSkyTest.java, Interval.java, CustomQueue.java: Practice problems inspired by Amazon interview
      patterns.

This convention keeps historical problems and company-specific practice exercises separated while allowing reuse of
shared utilities.

## Build and Run

Prerequisites:

- Java 17 installed and available on PATH
- Maven 3.8+ installed

Common commands (run from repository root):

- Build (compile + test):
  mvn clean install
- Run Spring Boot app (if needed):
  mvn spring-boot:run
- Execute a specific main class directly (example):
  mvn -Dexec.mainClass=com.interview.notes.code.year.y2025.september.assesment.test6.Solution \
  -Dexec.classpathScope=runtime \
  org.codehaus.mojo:exec-maven-plugin:3.1.0:java

Alternatively, many solutions include a public static void main(String[] args) entry point and can be executed from your
IDE.

## Testing

- To run all tests:
  mvn test
- Surefire reports will be available in target/surefire-reports.
- For individual tests, use your IDE’s test runner or mvn -Dtest=FullyQualifiedTestName test.

## Code Formatting

- This project uses Google Java Format via the fmt-maven-plugin to keep Java code consistent.
- To format the code automatically:
  - Using Maven directly:
    mvn com.coveo:fmt-maven-plugin:format
  - Or using the helper script:
    ./scripts/format.sh
- To fail the build if formatting is needed:
  mvn com.coveo:fmt-maven-plugin:check

## Git: Commit and Push

You can commit and push your changes with the following commands. Make sure you have a GitHub repository created and that your local repo has a remote set.

1) Configure Git (once per machine):
   git config --global user.name "Your Name"
   git config --global user.email "you@example.com"

2) Initialize repo (if not already):
   git init

3) Add a remote (replace with your repository URL):
   git remote add origin https://github.com/<your-user>/<your-repo>.git
   # or use SSH
   # git remote add origin git@github.com:<your-user>/<your-repo>.git

4) Stage, commit, and push:
   git add -A
   git commit -m "chore: format code and update README"
   git push -u origin main
   # If your default branch is master, use master instead of main.

Notes:
- Pushing to GitHub requires that you have permission and are authenticated. For HTTPS, use a Personal Access Token (PAT) as your password if prompted. For SSH, ensure your SSH key is added to your GitHub account.
- From this automated environment, we cannot directly push to your GitHub without your credentials. Run the above commands locally to push.

## Coding Guidelines and Conventions

- Prefer clear, readable solutions with comments explaining business rules and edge cases.
- Use immutable data where possible and avoid side effects.
- Handle nulls defensively; fail closed, not open.
- Include tie-breaker rules and deterministic ordering if required by problem statements.
- Add simple smoke tests or a lightweight test harness in classes that interact with external services.
- Keep methods small and single-purpose; favor pure functions for algorithmic problems.

## Notable Modules and Examples (Deep Dive)

1) API Pagination + Filtering Example: Solution.java

- Location: com.interview.notes.code.year.y2025.september.assesment.test6.Solution
- Problem: Find the longest-duration event organized by a given organizer that matches a specific genre.
- Highlights:
    - Uses Java HttpClient and Gson to fetch and parse paginated JSON from https://jsonmock.hackerrank.com/api/events.
    - Case-insensitive matching of organizer and genre.
    - Robust comparator: duration (null treated as 0) descending, tie-break by id ascending.
    - Returns "-1" on missing matches or any network/parse errors.
    - Includes a test() method for quick checks and a main() entry point for manual runs.
- Example run (console):
  mvn -Dexec.mainClass=com.interview.notes.code.year.y2025.september.assesment.test6.Solution \
  -Dexec.classpathScope=runtime \
  org.codehaus.mojo:exec-maven-plugin:3.1.0:java

2) Amazon-style Problems:

- NumberofAirplanesSkyTest.java and Interval.java demonstrate interval merging/scanline ideas.
- CustomQueue.java shows building a queue with custom constraints helpful in DS interviews.

## Run Individual Examples (Common)

You can run any class with a public static void main(String[] args) using the Exec Maven Plugin. Below are some handy
examples from recent additions:

- Payment processing demo (common/test4/Main):
  mvn -Dexec.mainClass=com.interview.notes.code.year.y2025.september.common.test4.Main \
  -Dexec.classpathScope=runtime \
  org.codehaus.mojo:exec-maven-plugin:3.1.0:java

- Third highest number finder (common/test4/ThirdHighestNumberFinder):
  mvn -Dexec.mainClass=com.interview.notes.code.year.y2025.september.common.test4.ThirdHighestNumberFinder \
  -Dexec.classpathScope=runtime \
  org.codehaus.mojo:exec-maven-plugin:3.1.0:java

- Longest unique substring tester (common/test6/LongestUniqueSubstringTester):
  mvn -Dexec.mainClass=com.interview.notes.code.year.y2025.september.common.test6.LongestUniqueSubstringTester \
  -Dexec.classpathScope=runtime \
  org.codehaus.mojo:exec-maven-plugin:3.1.0:java

Tip: If the package path changes over time (e.g., new month/year folders), adjust the -Dexec.mainClass accordingly, or
run directly from your IDE.

## Push to GitHub

Follow these steps to push this project to your GitHub repository.

1) Initialize Git (if not already a repo):
   git init

2) Create a new empty repository on GitHub (do not add README/License). Copy its URL, e.g.:
   https://github.com/<your-username>/<your-repo>.git

3) Add remote and set the default branch name (main):
   git remote add origin https://github.com/<your-username>/<your-repo>.git
   git branch -M main

4) Commit and push your changes:
   git add -A
   git commit -m "Initial commit"
   git push -u origin main

Alternatively, use the helper script included in this repo:

- First time (sets origin and pushes to main):
  scripts/push.sh "Initial commit" https://github.com/<your-username>/<your-repo>.git main

- Subsequent pushes (origin already set):
  scripts/push.sh "Update: <what changed>"

Notes:

- Make sure you are authenticated with GitHub (HTTPS with Personal Access Token or SSH).
- The .gitignore in this repo excludes build artifacts (target/), IDE files, OS files, and other non-source items.

## How to Contribute

- Fork the repository and create a feature branch.
- Follow the coding guidelines above.
- Include comments and small test cases to explain tricky parts.
- Open a pull request with a clear description of changes and rationale.

## Troubleshooting

- Maven cannot find Java 17:
    - Ensure JAVA_HOME points to JDK 17 and your PATH uses that JDK (java -version should show 17).
- Tests fail due to ports in use (for any web tests):
    - Stop other local servers or set a different server.port in application.properties.
- SSL/network errors when running API-based examples:
    - These examples depend on public endpoints; retry later or ensure your network allows outbound HTTPS.
- Exec plugin error when running a main class:
    - Ensure the exec-maven-plugin goal specified in the command matches the version declared in the README (3.1.0 in
      examples).

## FAQ

- Q: Can I run a single solution without the Spring Boot app?
  A: Yes. Many classes contain a main method. Use the Exec Maven Plugin example above or run via IDE.
- Q: Do I need internet access for some solutions?
  A: Only for problems that intentionally call public APIs (e.g., Solution.java). Others are self-contained.

## License

If a LICENSE file exists at the repository root, the project is distributed under that license. If not, treat the code
as All Rights Reserved by the repository owner unless otherwise specified.

---
Last updated: 2025-09-18