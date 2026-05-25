# 🃏 Deck of Cards API Test Automation Framework

A production-grade API test automation framework built to validate the [Deck of Cards API](https://deckofcardsapi.com/). Built with Java, Maven, Cucumber BDD, TestNG, and ExtentReports — demonstrating end-to-end API test automation with data-driven testing, structured logging, and HTML reporting.

---

## 🎯 Project Overview

This framework automates API testing for the Deck of Cards public API, covering deck creation, card shuffling, card drawing, and full deck validation. It uses Behavior-Driven Development (BDD) with Cucumber feature files so test scenarios are readable by both technical and non-technical stakeholders.

---

## 🏗️ Framework Architecture

```
deckofcards/
├── src/
│   ├── main/
│   │   ├── java/com/qa/sqm/
│   │   │   ├── api/
│   │   │   │   └── DeckofCardsPage.java      # Page Object — API actions
│   │   │   ├── testrunner/
│   │   │   │   ├── RunCukes.java             # TestNG Cucumber runner
│   │   │   │   ├── TestContext.java          # Shared test context (thread-safe)
│   │   │   │   └── MyContainer.java          # Dependency injection container
│   │   │   └── utilities/
│   │   │       ├── Service.java              # HTTP client — GET/POST requests
│   │   │       ├── ConfigData.java           # Config properties reader
│   │   │       └── ExcelData.java            # Test data reader (Apache POI)
│   │   └── resources/
│   │       ├── InputData/TestData.xlsx       # Expected card data
│   │       ├── config/config.properties      # API endpoints configuration
│   │       └── log4j.properties              # Logger configuration
│   └── test/
│       ├── java/stepdefinition/
│       │   └── DeckOfCardsStep.java          # Cucumber step definitions
│       └── resources/features/
│           └── DeckOfCards.feature           # BDD test scenarios
├── testng.xml                                 # TestNG suite configuration
└── pom.xml                                    # Maven dependencies
```

---

## 🧪 Test Scenarios

### TC_01 — Create a New Standard Deck

```gherkin
Scenario: Create a New Deck of Cards and Validate
  Given I GET New Deck of Cards using open API
  When I Validate the response with Success as "true" and Shuffle Status
  Then I get the deckid and total cards count
  And I shuffle the cards and validate the Shuffle Status
  And I Validate all the Cards in the Deck
```

**Validates:**
- API returns `success: true` and correct shuffle status
- Deck ID and remaining card count (52) are correct
- All 52 cards present with correct codes and suits (SPADES, HEARTS, CLUBS, DIAMONDS)

---

### TC_02 — Create a Deck with Jokers

```gherkin
Scenario: Create a New Deck of Cards with Jokers and Validate
  Given I POST New Deck of Cards with Jokers using open API
  When I Validate the response with Success as "true" and Shuffle Status
  Then I get the deckid and total cards count
  And I shuffle the cards and validate the Shuffle Status
  And I Validate all the Cards in the Deck
```

**Validates:**
- Deck created with 54 cards (52 + 2 Jokers)
- Jokers present: `X1 (BLACK)` and `X2 (RED)`
- All card codes and suits validated against expected data from Excel

---

### TC_03 — Draw Cards and Validate Remaining Count

```gherkin
Scenario: Draw a Card from the New Deck of Cards and Validate the remaining cards
  Given I GET New Deck of Cards using open API
  ...
  And Draw "1" cards and validate the remaining cards
  And Draw "2" cards and validate the remaining cards
  And Draw "3" cards and validate the remaining cards
```

**Validates:**
- Cards drawn correctly match requested count
- Remaining card count decrements correctly after each draw
- Card codes and suits returned correctly for drawn cards

---

## 🔧 Tech Stack

| Tool | Purpose | Version |
|------|---------|---------|
| Java | Programming language | 1.8 |
| Maven | Build and dependency management | 3.x |
| Cucumber | BDD framework | 1.2.4 |
| TestNG | Test execution engine | 6.9.9 |
| Apache HttpClient | HTTP GET/POST requests | 4.5.3 |
| ExtentReports | HTML test reporting | 2.41.0 |
| Log4j | Execution logging | 1.2.17 |
| Apache POI | Excel test data reading | 3.13 |
| Google Guice | Dependency injection | 3.0 |
| JSON Simple | JSON parsing | 1.1 |

---

## 🐛 Defects Identified

### Defect 1 — Joker Count Discrepancy After Shuffle
**Description:** When a new deck is created with Jokers, the response shows `remaining: 54`. However, after shuffling the deck, the response shows `remaining: 52` — the 2 Jokers are silently removed during shuffle.

**Steps to Reproduce:**
1. POST `https://deckofcardsapi.com/api/deck/new?jokers_enabled=true`
2. Note `remaining: 54`
3. GET shuffle endpoint for same deck_id
4. Note `remaining: 52` — Jokers removed

**Expected:** `remaining: 54` after shuffle
**Actual:** `remaining: 52` after shuffle

---

### Defect 2 — Joker Draw Limit After Shuffle
**Description:** When drawing cards from a Joker deck after shuffling, maximum drawable cards is 52, not 54. Without shuffling, all 54 cards can be drawn.

**Expected:** 54 cards drawable from a Joker deck (with or without shuffle)
**Actual:** Only 52 cards drawable after shuffling a Joker deck

---

## 🚀 How to Execute

### Prerequisites

1. **Install Java 8** — [Download](https://www.guru99.com/install-java.html)
2. **Install Maven** — [Download](https://mkyong.com/maven/how-to-install-maven-in-windows/)
3. **Install IntelliJ IDEA** — [Download](https://java.tutorials24x7.com/blog/how-to-install-intellij-idea-for-java-on-windows)

### Run from Command Line

```bash
# Navigate to project root (where pom.xml is located)
cd deckofcards

# Clean, build, and execute all tests
mvn clean install test
```

### Run from IntelliJ IDEA

1. Open project in IntelliJ
2. Right-click `testng.xml` → **Run**

   OR

3. Open Terminal in IntelliJ → type `mvn clean install test`

---

## 📊 Reports and Logs

### HTML Report
After execution, an HTML report is generated in the `target/` folder using ExtentReports. It shows:
- Each test scenario with pass/fail status
- All executed steps with request URLs and response data
- Execution timestamps and duration

### Logs
A `logs/` file is generated in the project root. Open in any text editor to view:
- API request URLs for each step
- HTTP response status codes
- Response payload data
- Any exceptions or failures

---

## ⚙️ Configuration

API endpoints are configured in `src/main/resources/config/config.properties`:

```properties
NewDeck=https://deckofcardsapi.com/api/deck/new/
Shuffle=https://deckofcardsapi.com/api/deck/
NewDeckWithJokers=https://deckofcardsapi.com/api/deck/new?jokers_enabled=true
DrawACard=https://deckofcardsapi.com/api/deck/
```

---

## 🛠️ Tech Stack Badges

![Java](https://img.shields.io/badge/Java-1.8-orange?style=flat&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat&logo=apache-maven)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green?style=flat&logo=cucumber)
![TestNG](https://img.shields.io/badge/TestNG-6.9.9-blue?style=flat)
![ExtentReports](https://img.shields.io/badge/ExtentReports-HTML-purple?style=flat)

---

## 👤 Author

**Praveen Kumar Jatta** — AI Automation Consultant & Technical Program Manager

- 🌐 [jattaai.com](https://jattaai.com)
- 💼 [linkedin.com/in/praveenjatta](https://linkedin.com/in/praveenjatta)
- 📧 jattaaihq@gmail.com

---

## 📝 Notes

- This project targets the public [Deck of Cards API](https://deckofcardsapi.com/) — no API key required
- Test data (expected card values) is stored in `src/main/resources/InputData/TestData.xlsx`
- Framework uses thread-safe `TestContext` with Google Guice dependency injection for parallel execution support
