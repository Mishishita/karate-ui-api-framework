# Karate UI API Framework

UI and API automation framework built with Playwright, Karate, Java, Maven and GitHub Actions.

## Overview

This project demonstrates an end-to-end automation framework that combines:

* UI automation using Playwright
* API automation using Karate Framework
* Maven build management
* GitHub Actions CI pipeline
* End-to-End test execution

The framework automates the complete customer journey:

1. User registration through the UI
2. Customer validation through APIs
3. Savings account creation or reuse
4. Deposit execution
5. Funds transfer validation

---

## Tech Stack

* Java 21
* Playwright
* Karate Framework
* Maven
* JUnit 5
* GitHub Actions
* Git

---

## Project Structure

```text
src
└── test
    ├── java
    │   ├── runners
    │   ├── tests
    │   └── utils
    │
    └── resources
        ├── api
        ├── data
        └── features
```

---

## Execute UI Test

```bash
mvn -Dtest=RegistroUsuarioPWTest test
```

---

## Execute API End-to-End Flow

```bash
mvn -Dtest=TestRunner test
```

---

## Continuous Integration

This project uses GitHub Actions to automatically:

* Install Java 21
* Install Playwright browsers
* Execute UI registration flow
* Execute Karate API End-to-End flow

Every push and pull request triggers the CI pipeline.

---

## Features

* UI Automation
* API Automation
* End-to-End Testing
* Dynamic Test Data
* Reusable Test Components
* CI/CD Integration

---

## Author

Mariluz Tinoco

QA Automation Engineer
