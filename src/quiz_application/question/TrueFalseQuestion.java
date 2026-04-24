package quiz_application.question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TrueFalseQuestion implements Question {
    private String question;
    private boolean correctAnswer;
    private Boolean lastUserAnswer = null;

    public TrueFalseQuestion(String question, boolean answer) {
        this.question = question;
        this.correctAnswer = answer;
    }

    public TrueFalseQuestion(BufferedReader reader) {
        try {
            String line = reader.readLine();
            if (line == null)
                throw new IOException("EOF reached");

            this.question = line;


            line = reader.readLine();
            if (line == null)
                throw new IOException("EOF reached");

            this.correctAnswer = Boolean.parseBoolean(line);

            if (!reader.readLine().isBlank())
                throw new IOException("Syntax Error in file");

        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
            
            this.question = "";
            this.correctAnswer = false;
        }
    }

    @Override
    public double askAndGetScore(Scanner input, int number) {

        boolean userInput;
        while (true) {
            System.out.print("\n" + number + ". question> " + this.question + " [allowed: true/false]\nanswer> ");
            try {
                boolean tmpInput = input.nextBoolean();
                userInput = tmpInput;
                break;
            } catch (Exception e) {
                System.err.println("Invalid input: Should be 'true' or 'false'");
                input.next();
            }
        }

        this.lastUserAnswer = userInput;
        return (userInput == this.correctAnswer) ? 1 : 0;
    }

    @Override
    public String getAnswerOverview() {
        String answerText = lastUserAnswer.toString();
        String correctText = Boolean.toString(correctAnswer);
        String resultText = (lastUserAnswer == correctAnswer) ? "Correct" : "Incorrect";

        return "question: " + this.question + "\n" +
                "Your answer: " + answerText + "\n" +
                "Correct answer: " + correctText + "\n" +
                "Result: " + resultText;
    }

    @Override
    public void printQuestionAndAnswer(int number) {
        System.err.println("" + number + ". question: " + this.question + "\nanswer: " + this.correctAnswer);
    }

    @Override
    public boolean save(PrintWriter writer) {
        writer.format("TrueFalseQuestion\n%s\n%b\n\n", this.question, this.correctAnswer);

        return true;
    }
}
