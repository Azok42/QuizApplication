# QuizApplication
A minimalist, console-based Java quiz application with hardcoded questions.

## Supported Question types
- true-false
- single choice
- multiple choice

## Prerequisites

- Java Development Kit (JDK) 8 or higher.

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
java -cp ".:../lib/json-java.jar" quiz_application.Quiz print # for print mode 
```