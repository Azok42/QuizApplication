package quiz_application.question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MultipleChoiceQuestion extends ChoiceQuestion {
    private Integer[] correctAnswers;

    public MultipleChoiceQuestion(String question, Integer[] correctAnswers, String[] answers) {
        super(question, answers);
        this.correctAnswers = correctAnswers;
    }

    public MultipleChoiceQuestion(BufferedReader reader) {
        super(reader);

        try {
            String line;
            int i = 0;
            ArrayList<Integer> tmpCorrectAnswers = new ArrayList<>();

            while (true) {
                line = reader.readLine();
                if (line == null)
                    throw new IOException("EOF reached");

                if (line.isBlank())
                    break;

                if (line.startsWith("+"))
                    tmpCorrectAnswers.add(i + 1);

                this.answers[i++] = line.replaceFirst("^[+-]:\\s", "");
            }

            this.correctAnswers = new Integer[tmpCorrectAnswers.size()];
            for (int j = 0; j < tmpCorrectAnswers.size(); j++) {
                this.correctAnswers[j] = tmpCorrectAnswers.get(j);
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        }
    }

    @Override
    public double askAndGetScore(Scanner input) {
        System.out.println("\nquestion> " + this.question + " [allowed: 1-4, 0 for ending]");

        printAnswers();
                
        ArrayList<Integer> correctAnswersCopy = new ArrayList<>();
        correctAnswersCopy.addAll(Arrays.asList(correctAnswers));

        int tmpInput;
        double result = 0;
        for (int i = 0; i < 4 && (tmpInput = getUserInput(input)) != 0; i++) {
            boolean correctAnswer = false;
            for (int j = 0; j < correctAnswersCopy.size(); j++) {
                if (tmpInput != correctAnswersCopy.get(j))
                    continue;

                correctAnswer = true;
                correctAnswersCopy.remove(j);
                break;
            }

            if (correctAnswer)
                result++;
            else
                result--;
        }

        result /= correctAnswers.length;
        return result;
    }

    @Override
    public void printQuestionAndAnswer() {
        System.out.println("question: " + this.question);
        System.out.println("answers: ");
        for (int correctIndex : correctAnswers) {
            System.out.println("\t" + answers[correctIndex - 1]);
        }
    }

    @Override
    public boolean save(PrintWriter writer) {
        writer.write("MultipleChoiceQuestion\n");
        writer.write(this.question + "\n");

        for (int i = 0; i < 4; i++) {
            boolean correct = false;
            for (Integer num : correctAnswers) {
                correct = correct || (num == i + 1);
            }
            
            writer.format("%s: %s\n", correct ? "+" : "-", this.answers[i]);
        }

        writer.write("\n");
        return true;
    }
}
