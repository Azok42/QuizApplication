package quiz_application.question;

import java.util.Scanner;

public class TrueFalseQuestion implements Question {
    private final String question;
    private final boolean correctAnswer;

    public TrueFalseQuestion(String question, boolean answer) {
        this.question = question;
        this.correctAnswer = answer;
    }

    @Override
    public double askAndGetScore(Scanner input) {

        boolean userInput;
        while (true) {
            System.out.print("\nquestion> " + this.question + " [allowed: true/false]\nanswer> ");
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

    @Override
    public void printQuestionAndAnswer() {
        System.err.println("question: " + this.question + "\nanswer: " + this.correctAnswer);
    }
}
