package quiz_application.question;

import java.util.Scanner;

public abstract class ChoiceQuestion implements Question {
    protected final String question;
    protected final String[] answers;

    public ChoiceQuestion(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    protected int getUserInput(Scanner input) {
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
        return userInput;
    }
}
