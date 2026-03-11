package quiz_application.question;

import java.util.Scanner;

public class Question {
    private final String question;
    private final boolean correctAnswer;

    public Question(String question, boolean answer) {
        this.question = question;
        this.correctAnswer = answer;
    }

    public double askAndGetScore(Scanner input) {

        boolean userInput;
        while (true) {
            System.out.print("question: " + this.question + " [allowed: true/false] answer> ");
            try {
                boolean tmpInput = input.nextBoolean();
                userInput = tmpInput; 
                break;
            } catch (Exception e) {
                System.err.println("Invalid input: Should be 'true' or 'false'");
                input.next();
            }
        }

        return (userInput == this.correctAnswer) ? 1 : 0;
    }

    public void printQuestionAndAnswer() {
        System.err.println("question: " + this.question + " answer: " + this.correctAnswer);
    }
}
