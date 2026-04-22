package quiz_application.question;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public abstract class ChoiceQuestion implements Question {
    protected String question;
    protected String[] answers;

    public ChoiceQuestion(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }
    
    public ChoiceQuestion(BufferedReader reader) {
        try {
            String line = reader.readLine();
            if (line == null)
                throw new IOException("EOF reached");

            this.question = line;
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
            this.question = "";
        }

        this.answers = new String[4];
    }

    protected void printAnswers() {
        for (int i = 0; i < this.answers.length; i++)
            System.out.println("\t" + (i+1) + ". " + this.answers[i]);
    }

    protected int getUserInput(Scanner input) {
        int userInput;
        while (true) {
            System.out.print("answer> ");
            try {
                int tmpInput = input.nextInt();

                if (tmpInput < 0 || tmpInput > 4) {
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
