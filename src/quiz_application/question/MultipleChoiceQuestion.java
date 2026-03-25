package quiz_application.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MultipleChoiceQuestion extends ChoiceQuestion {
    private final Integer[] correctAnswers;

    public MultipleChoiceQuestion(String question, Integer[] correctAnswers, String[] answers) {
        super(question, answers);
        this.correctAnswers = correctAnswers;
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

}
