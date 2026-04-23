# QuizApplication
A minimalist, console-based Java quiz application with questions inside a json file.

## Supported Question types
- true-false
- single choice
- multiple choice
- Q1

## Prerequisites

- Java Development Kit (JDK) 8 or higher.

## Quick Method

Head to the releases and download the .jar file.

Next go to your terminal and in the directory where you downloaded the file run:

```bash
java -jar QuizApplication.jar
# or of course any other mode
```

## Getting Started

- Clone: 
```bash
git clone https://github.com/Azok42/QuizApplication.git
```

- Compile: 
```bash
cd QuizApplication
javac -cp "lib/json-java.jar:src" -d bin src/quiz_application/**/*.java
```

- Run:
```bash
cd bin

java -cp ".:../lib/json-java.jar" quiz_application.Quiz
# OR
java -cp ".:../lib/json-java.jar" quiz_application.Quiz help # for help because you will need it if you want to use this crap
```