## OrangeHRM Selenium Mini Framework

A mini test automation framework built with Java + Selenium + TestNG + Maven using the Page Object Model design pattern.  
This project was created to automate core end-to-end scenarios in the OrangeHRM Demo application.

## Project Scope

The framework covers the following test scenarios:
- valid login
- invalid login
- add employee
- search employee
- edit employee data
- delete employee

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager

## Implemented Features

This project includes:
- Page Object Model
- explicit waits
- retry logic
- DataProvider
- test grouping with `smoke` and `regression`
- automatic screenshots for failed tests

## Project Structure

```text
src/test/java
├── base
│   └── BaseTest.java
├── models
│   └── Employee.java
├── pages
│   ├── BasePage.java
│   ├── LoginPage.java
│   ├── DashboardPage.java
│   ├── EmployeeListPage.java
│   ├── AddEmployeePage.java
│   └── PersonalDetailsPage.java
├── tests
│   ├── LoginTests.java
│   └── EmployeeCrudTests.java
└── utils
    ├── DriverFactory.java
    ├── RetryAnalyzer.java
    ├── ScreenshotUtils.java
    └── TestDataFactory.java

src/test/resources
└── testng.xml
```

## Application Under Test

This framework was prepared for the OrangeHRM demo environment:
- URL: `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`
Test credentials:
- Username: `Admin`
- Password: `admin123`

## Requirements

To run this project, make sure you have:
- Java 17 installed
- Maven installed
- Google Chrome installed

## Installation

Clone the repository:
```bash
git clone https://github.com/your-username/your-repository.git
```
Go to the project folder:
```bash
cd your-repository
```
Run the tests:
```bash
mvn clean test
```

## Running Tests

Run all tests:
```bash
mvn clean test
```

## Implemented Test Cases

LoginTests
- `test_valid_login`
- `test_invalid_login`

EmployeeCrudTests
- `test_add_employee`
- `test_search_employee`
- `test_edit_employee`
- `test_delete_employee`

## Failure Screenshots

If a test fails, the framework automatically saves a screenshot in:
```text
screenshots/
```

##Possible Improvements

This project can be extended with:
- multi-browser support
- `config.properties`
- test reports
- CI/CD integration
- more advanced test data management
- form validation scenarios
- employee photo upload tests

## Project Goal

The goal of this project was to build a simple and readable Selenium mini framework that demonstrates core automation testing practices and provides a solid base for adding more test scenarios.

## Author
Patrycja Brodzik
