# Selenium_Coocumber_UI_Automation



## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin url
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](URL)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing(SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

## Name
UI Automation Project

## Description
UI Automation Project is designed for UI automating test cases using Cucumber, TestNG, and Selenium WebDriver. This framework supports structured testing with separate modules for utilities, page classes, step definitions, listeners, and test runners. The project is aimed at streamlining the testing process for web applications, providing a robust and maintainable solution for automated testing.

## Guidelin and Rule
This repository is designed for automating test cases using Cucumber with TestNG and Selenium WebDriver. The project follows a modular structure, with separate folders for utilities, page classes, step definitions, listeners, and test runners.

```
UI_Automation
├── .idea
├── logs
├── screenshots
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── commonUtils
│   │   │   │   ├── Config.java
│   │   │   │   ├── DriverUtil.java
│   │   │   │   ├── ScreenshotUtil.java
│   │   │   │   ├── TestData.java
│   │   │   │   └── WaitUtil.java
│   │   │   ├── pageClass
│   │   │   │   ├── BasePage.java
│   │   │   │   └── RegistrationPage.java
│   │   └── resources
│   │       └── log4j2.xml
│   ├── test
│   │   ├── java
│   │   │   ├── listeners
│   │   │   │   └── TestListener.java
│   │   │   ├── runners
│   │   │   │   └── TestRunner.java
│   │   │   └── stepDefinitions
│   │   │       ├── Hooks.java
│   │   │       └── RegistrationStepDefinitions.java
│   │   └── resources
│   │       └── features
│   │           └── Registration.feature
└── target
└── test-output
```


## Getting Started

## Prerequisites

- Java 8 or higher JAVA 17
- Maven 3.9.9
- An IDE (e.g., IntelliJ IDEA, Eclipse)
- ChromeDriver or appropriate WebDriver for your browser


## Installation

`git clone url
`cd UI_Automation`

##  Install dependencies with Maven
`mvn clean install`

## Configuration
**Configuring Config.java:**

Update the Config.java file in the commonUtils package with the base URL of your application and other configuration settings (e.g., timeouts).

**Setting up WebDriver:**

Ensure you have the correct WebDriver installed (e.g., ChromeDriver for Chrome).
Configure the path in the DriverUtil.java class if necessary.

**Running Tests**
To execute tests, use the following Maven command:
`mvn test`

Alternatively, you can run tests from your IDE using the TestRunner.java class located in `src/test/java/runners`

## Project Components

**1. Common Utilities (commonUtils)**
- Config.java: Holds configuration data like base URL and timeout settings.
- DriverUtil.java: Manages WebDriver initialization and teardown.
- ScreenshotUtil.java: Takes screenshots during test failures for easier debugging.
- TestData.java: Singleton class for sharing test data across step definitions, e.g., a generated email to be reused in different steps.
- WaitUtil.java: Contains custom wait methods to handle element visibility, clickability, and invisibility, improving test stability.

**Base Page (pageClass)**
**BasePage.java:**
`Provides reusable methods for common WebDriver actions such as clickElement, enterText, and getText.`
The waitForElement method in BasePage ensures elements are visible before interaction, leveraging WaitUtil.

```
clickElement(By locator);      // Clicks on an element
enterText(By locator, text);   // Enters text into an input field
getText(By locator);           // Retrieves text from an element
```
**RegistrationPage.java:**
- Contains methods specific to the registration page, using BasePage methods to interact with elements.
- Handles filling out the registration form and submitting it.


**3. Step Definitions (stepDefinitions)**
**RegistrationStepDefinitions.java:**
- Defines the Cucumber steps for the Registration.feature file.
- Uses methods from RegistrationPage to perform actions, making each step clear and concise.


**Example of a Step Definition:**

```
@When("the user fills in the registration form with the following details:")
public void fillRegistrationForm(io.cucumber.datatable.DataTable dataTable) {
    // Calls RegistrationPage to populate fields
}
```
**Hooks.java:**

Contains @Before and @After hooks for setup and teardown actions, such as initializing and quitting the WebDriver.

**4. Runners (runners)**
**TestRunner.java:**
- Configures and runs Cucumber tests.
- Specifies the feature files and step definition packages to be used.

**5. Listeners (listeners)**
**TestListener.java:**
Implements TestNG listeners for logging, reporting, and taking screenshots on test failures.

**6. Feature Files (features)**
**Registration.feature:**

- Contains Gherkin syntax for defining test scenarios. This example includes a scenario for corporate registration.
- Each scenario maps to step definitions in RegistrationStepDefinitions.java.


## Usage Guidelines

**Writing New Tests:**
- Write new scenarios in `.feature` files under s`src/test/resources/features`.
- Create corresponding step definitions in `stepDefinitions` to handle the test steps.

**Adding Page Classes:**
- For any new page in the application, create a new class in `pageClass` and extend `BasePage`.
- Use methods from `BasePage` for common actions (e.g., `clickElement`, `getText`), and add page-specific methods as needed.


**Sharing Data Across Steps:**
Use` TestData.java `to store any data (like a generated email) that needs to be shared across multiple steps or scenarios.
