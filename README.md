# Scialex Results Scraper

---

## Disclaimer: This tool is intended for personal use. Ensure you have permission to automate interactions with the scialex portal and comply with their Terms of Service. The author is not responsible for any misuse or damage resulting from this tool.

---

A Java-based web scraper that automates the process of logging into the [scialex](https://www.scialex.org/) student portal, retrieves academic results, parses the data, and exports it to a structured output file. This tool uses Selenium WebDriver for browser automation.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [License](#license)


## Features

- **Automated Login**: Securely logs into the scialex portal using provided credentials.
- **Dynamic Content Handling**: Utilizes explicit waits to handle dynamic web elements.
- **Data Extraction**: Retrieves detailed academic results, including GPA, CGPA, and course-wise breakdown.
- **Table Parsing**: Employs regular expressions to accurately parse and structure the extracted data.
- **Error Handling**: Comprehensive error logging for unmatched or malformed records.
- **Output Generation**: Writes the parsed data to a specified output file for easy access and analysis.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK)**: Version 8 or higher. [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven**: For managing project dependencies and building the project. [Install Maven](https://maven.apache.org/install.html)
- **Environment Variables**:
    - `LOGIN_USERNAME`: Your scialex portal username.
    - `LOGIN_PASSWORD`: Your scialex portal password.
- **Internet Connection**: Required for Selenium WebDriver to access the scialex website.
- **Edge Browser**: Ensure Microsoft Edge is installed, as the project uses the EdgeDriver. [Download Edge](https://www.microsoft.com/edge)

## Installation

1. **Clone the Repository**

   ```shell
   git clone https://github.com/MohamedWElteir/scialex-results-scraper.git
   cd scialex-results-scraper
   ```
2. **Build the project**

- The project uses Maven for dependency management. Build the project using:
```bash
  mvn clean install
```
3. **Set Up Environment Variables**

- Create a .env file in the `src/main/resources` directory with the following content:
```dotenv
LOGIN_USERNAME=your_username
LOGIN_PASSWORD=your_password
```

## Configuration

The scraper is configured to use the Microsoft Edge browser via Selenium WebDriver. Ensure that the Edge browser is installed on your machine. The `WebDriverManager` library automatically manages the browser driver binaries.

If you prefer to use a different browser (e.g., Chrome), modify the `Main.java` accordingly:

1. **Update Dependencies**

- Ensure you have the appropriate WebDriver for your chosen browser. For example, add the following to your `pom.xml` for Chrome:
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-chrome-driver</artifactId>
    <version>4.8.0</version>
</dependency>
```
2. **Modify the WebDriver Initialization**
- In `main.java`, replace the EdgeDriver setup with ChromeDriver:
```java
// For Chrome
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
```
## Usage

1. **Navigate to the project's directory**
```bash
cd scialex-results-scraper
```
2. **Run the application**
- You can run the application using Maven:
```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
```
- Or, if you prefer using the compiled JAR:

```bash
java -jar target/scialex-results-scraper-1.0-SNAPSHOT.jar
```

## Project Structure

```html
scialex-results-scraper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org.example/
│   │   │       ├── Main.java
│   │   │       ├── ResultsRow.java
│   │   │       └── ResultsTable.java
│   │   └── resources/
│   │       ├── output/
│   │       └── .env
├── pom.xml
└── README.md
```

## License
### This project is licensed under the MIT License.
