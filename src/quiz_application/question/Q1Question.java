package quiz_application.question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Q1Question extends ChoiceQuestion {
    private String target;
    private int wrongHint;
    // answers from ChoiceQuestion is used as hints here

    public Q1Question(String question, int wrongHint, String[] hints, String target) {
        super(question, hints);
        this.target = target;
        this.wrongHint = wrongHint;
    }

    public Q1Question(BufferedReader reader) {
        super(reader);

        try {
            String line;
            int i = 0;

            while (true) {
                line = reader.readLine();
                if (line == null)
                    throw new IOException("EOF reached");

                if (line.isBlank() || (!line.startsWith("+") && !line.startsWith("-")))
                    break;

                if (line.startsWith("-"))
                    this.wrongHint = i + 1;

                this.answers[i++] = line.replaceFirst("^[+-]:\\s", "");
            }

            this.target = line;

        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
            this.wrongHint = -1;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public double askAndGetScore(Scanner input, int number) {
        System.err.println("\n" + number + ". question> " + this.question + "[allowed: 1-4 for the wrong hint]");

        printAnswers();

        int result = 0;

        System.out.println("The wrong hint is: ");
        int wrontHintInput = getUserInput(input);
        if (wrontHintInput == this.wrongHint)
            result++;
        else
            result--;

        String answer;
        System.out.println("The searched term is: ");
        System.out.print("answer> ");
        answer = input.next();
        if (answer.equals(this.target))
            result++;
        else
            result--;

        return result / 2;
    }

    @Override
    public void printQuestionAndAnswer() {
        System.out.println("question: " + this.question);
        System.out.println("target: " + this.target);
        
        System.out.println("Correct hints:");
        for (int i = 0; i < answers.length; i++) {
            if (i == wrongHint)
                continue;
            System.out.println("\t" + (i+1) + ". hint: " + answers[i]);
        }

        System.out.println("Wrong hint: " + answers[wrongHint]);
    }

    @Override
    public boolean save(PrintWriter writer) {
        writer.write("Q1Question\n");
        writer.write(this.question + "\n");

        for (int i = 0; i < 4; i++)
            writer.format("%s: %s\n", ((i+1) == this.wrongHint) ? "-" : "+", this.answers[i]);

        writer.write(this.target + "\n");
        writer.write("\n");
        return true;
    }
}
