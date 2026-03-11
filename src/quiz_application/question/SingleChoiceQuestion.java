package quiz_application.question;

import java.util.Scanner;

public class SingleChoiceQuestion implements Question {
    private final String question;
    private final String[] possibilities;
    private final int correctAnswer;

    public SingleChoiceQuestion(String question, int answer, String[] possibilities) {
        this.question = question;
        this.correctAnswer = answer;
        this.possibilities = possibilities;
    }

    @Override
    public double askAndGetScore(Scanner input) {
        System.out.println("question> " + this.question + " [allowed: 1-4]");

        for (int i = 0; i < this.possibilities.length; i++) {
            System.out.println("\t" + (i+1) + ". " + this.possibilities[i]);
        }
        
        int userInput;
        while (true) {
            System.out.print("answer> ");
            try {
                int tmpInput = input.nextInt();

                if (tmpInput < 1 || tmpInput > 4) {
                    System.err.println("Invalid input: Should be between 1 and 4");
                    continue;
                }

                userInput = tmpInput; 
                break;
            } catch (Exception e) {
                System.err.println("Invalid input: Should be a number");
                input.next();
            }
        }

        return (userInput == this.correctAnswer) ? 1 : 0;
    }

    @Override
    public void printQuestionAndAnswer() {
        System.out.println("question: " + this.question + "\n answer: " + this.possibilities[this.correctAnswer-1]);
    }

}
