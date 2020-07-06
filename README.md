# deckofcards

This is an Automation Project Created to test deckofcards API. This Assignment will be submitted to FINRA.

  https://deckofcardsapi.com/

## Table of contents

1. [Tools](#1)

2. [Test Scenario's](#2)
    1. [TC_01](#2-1)
    2. [TC_02](#2-2)
    3. [TC_03](#2-3)

3. [How to Execute ?](#3)
    1. [Installations](#3-1)
    2. [Script Execution](#3-21)
    
4. [Reports & Logs](#4)    
    1. [Html Report](#4-1)
    2. [Logs](#4-2)
    
4. [Help](#5)      
    



## <a name="1"></a>Tools

Build tool :            Maven

Programming Language :  Java

BDD                  :  Cucumber

Reports              : ExtentReport

Logger               : Log4j

Execution            :  TestNG

API library          :  HttpClient

ExcelReading         :  Apache POI



## <a name="2"></a> Test Scenario's

### <a name="2-1"></a>TC_01
Create a New Deck of Cards and Validated the below

i) Validate the Success Status & Shuffle Status

ii) Get the deckid & Total cards to print

iii) Shuffle the Cards and Validate the Shuffle Status

iv) Validate all the Cards in the Deck

### <a name="2-2"></a>TC_02
Create a New Deck of Cards with Jokers and Validated the below

i) Validate the Success Status & Shuffle Status

ii) Get the deckid & Total cards to print

iii) Shuffle the Cards and Validate the Shuffle Status

iv) Validate all the Cards in the Deck

### <a name="2-3"></a>TC_03
Draw a Card from the Deck of Cards and Validate the Remaining cards

i) Draw one card and Validate the remaining cards

ii) Draw two cards again from the same deck and Validate the remaining cards



## <a name="3"></a> How to Execute ?

### <a name="3-1"></a>Installations

i) Download Java SDK, Install and set Environment Variables in Java: Path and Classpath from [here](https://www.guru99.com/install-java.html)

ii) Download Maven, Install and and configure the Windows environment variables. from [here](https://mkyong.com/maven/how-to-install-maven-in-windows/)

ii) Download IntelliJ and Install from [here](https://java.tutorials24x7.com/blog/how-to-install-intellij-idea-for-java-on-windows)

iv) Installing Plugins on IntelliJ from [here](https://www.jetbrains.com/help/idea/managing-plugins.html#plugin-dev)

v) Configure SDK on IntelliJ from [here](https://www.jetbrains.com/help/idea/sdk.html#change-project-sdk)


### <a name="3-2"></a>Script Execution

i) Open the project location on windows Explorer where you see pom.xml
type cmd on the address bar you will see a command prompt type mvn clean install test and enter to execute the scripts.

ii) Open the project in IntelliJ right click on the testng.xml annd click run

iii) Open the project in IntelliJ and click on the terminal and type mvn clean install test and click enter to execute the scripts.
 
  
  
## <a name="4"></a> Reports & Logs

### <a name="4-1"></a>Html Report

An Html Report will be generated in targer folder. This report is generated from extent report where you can see all the executed steps.

### <a name="4-2"></a>Logs

A logs file will be generated under the project which can opened in Notepad and view the execution logs.


## <a name="5"></a>Help

If you have any questions on this project, please send an email @ praveenjatta20@gmail.com


