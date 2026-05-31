![CI](https://github.com/PoojaSN-Automates/Selenium_Automation/actions/workflows/selenium-tests.yml/badge.svg)
# Selenium Automation Framework

## Project Overview

This project is a Hybrid Selenium Automation Framework developed using Java, Selenium WebDriver, TestNG, Maven, Jenkins, and GitHub Actions for automating the SauceDemo application.

The framework follows the Page Object Model (POM) design pattern and includes reusable utilities, reporting, listeners, retry mechanisms, Excel integration, screenshots, parallel execution, and CI/CD support.

---

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Jenkins
* GitHub Actions
* Git & GitHub
* Apache POI
* Extent Reports

---

## Framework Features

### Page Object Model (POM)

Implemented separate page classes for:

* Login Page
* Product Page
* Cart Page
* Checkout Page
* Menu Page
* Product Sorting Page

### TestNG Features

* Assertions
* DataProvider
* Parallel Execution
* Retry Failed Tests
* TestNG XML Execution
* Listeners

### Reporting

Implemented Extent Reports with:

* Pass/Fail Logging
* Screenshot Attachments
* System Information
* Execution Summary Dashboard
* Timeline View

### Screenshot Capture

Automatic screenshot capture for:

* Failed Tests
* Skipped Tests
* Timeout Failures

### Utilities

Reusable utility methods for:

* Explicit Waits
* Element Waits
* Alert Handling
* Screenshot Capture
* Excel Read/Write Operations

### Excel Integration

Implemented Data Driven Testing using Apache POI:

* Read test data from Excel
* Write PASS/FAIL status back to Excel

### Environment Configuration

Supports multiple environments using config.properties:

* QA
* UAT
* PROD

Environment can be selected dynamically during execution.

### Parallel Execution

Implemented ThreadLocal WebDriver for thread-safe parallel execution.

Example:

<suite name="SeleniumTests" parallel="methods" thread-count="3">

### CI/CD Integration

#### GitHub Actions

Configured GitHub Actions for:

* Automated Test Execution
* Headless Browser Execution
* Maven Build Execution
* Continuous Integration

#### Jenkins Pipeline

Configured Jenkins Pipeline for:

* Source Code Checkout
* Maven Build
* Test Execution
* Extent Report Publishing
* Screenshot Archiving

---

## Automated Test Scenarios

### Login Module

* Valid Login
* Invalid Login
* Locked User Validation

### Product Module

* Product Page Validation
* Add Product to Cart
* Remove Product from Cart

### Cart Module

* Cart Count Validation
* Single Product Addition
* Multiple Product Addition

### Checkout Module

* Complete Checkout Flow
* Invalid Checkout Validation
* Continue Shopping
* Cancel Checkout

### Menu Module

* Verify Menu Items
* Logout Validation

### Product Sorting Module

* Name (A-Z)
* Name (Z-A)
* Price (Low-High)
* Price (High-Low)

---

## Project Structure

src

├── main

│   ├── java

│   │   ├── base

│   │   ├── pages

│   │   ├── utils

│   │   └── resources

│

├── test

│   ├── java

│   │   ├── tests

│   │   ├── listeners

│   │   └── dataproviders

│

├── reports

├── screenshots

├── TestData

└── testng.xml

---

## Configuration

Framework configuration is maintained using:

config.properties

Example:

browser=chrome

environment=qa

username=standard_user

password=secret_sauce

timeout=30

headless=false

qa.url=https://www.saucedemo.com

uat.url=https://uat-app.com

prod.url=https://prod-app.com

---

## Run the Project

### Using Maven

mvn clean test

### Using TestNG XML

Right Click → testng.xml → Run As → TestNG Suite

### Run in Headless Mode

headless=true

### Select Environment

mvn test -Denvironment=qa

mvn test -Denvironment=uat

mvn test -Denvironment=prod

---

## Reports

Generated reports can be found in:

/reports

Includes:

* Extent Reports
* Execution Summary
* Pass/Fail Statistics
* Screenshots for Failed Tests

---

## Jenkins Pipeline

Pipeline Stages:

1. Checkout Source Code
2. Build Project
3. Execute Tests
4. Publish Extent Reports
5. Archive Screenshots

---

## GitHub Actions

Configured workflow for:

* Automated CI Execution
* Headless Chrome Execution
* Maven Build Verification

---

## Future Enhancements

* REST Assured API Automation Framework
* Docker Integration
* Selenium Grid
* Cross Browser Cloud Execution
* Database Validation
* Allure Reporting

---

## Author

Pooja

GitHub:
https://github.com/PoojaSN-Automates
