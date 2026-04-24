package quiz_application.question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SingleChoiceQuestion extends ChoiceQuestion {
    private int correctAnswer;

    public SingleChoiceQuestion(String question, int correctAnswer, String[] answers) {
        super(question, answers);
        this.correctAnswer = correctAnswer;
    }

    public SingleChoiceQuestion(BufferedReader reader) {
        super(reader);

        try {
            String line;
            int i = 0;

            while (true) {
                line = reader.readLine();
                if (line == null)
                    throw new IOException("EOF reached");

                if (line.isBlank())
                    break;

                if (line.startsWith("+"))
                    this.correctAnswer = i + 1;

                this.answers[i] = line.replaceFirst("^[+-]:\\s", "");

                i++;
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
            this.correctAnswer = -1;
        }
    }

    @Override
    public double askAndGetScore(Scanner input, int number) {
        System.out.println("\n" + number + ". question> " + this.question + " [allowed: 1-4]");

        printAnswers();
        
        int userInput = getUserInput(input);

        return (userInput == this.correctAnswer) ? 1 : 0;
    }

    @Override
    public void printQuestionAndAnswer() {
        System.out.println("question: " + this.question + "\nanswer: " + this.answers[this.correctAnswer - 1]);
    }

    @Override
    public boolean save(PrintWriter writer) {
        writer.write("SingleChoiceQuestion\n");
        writer.write(this.question + "\n");

        for (int i = 0; i < 4; i++)
            writer.format("%s: %s\n", ((i+1) == correctAnswer) ? "+" : "-", this.answers[i]);

        writer.write("\n");
        return true;
    }
}
