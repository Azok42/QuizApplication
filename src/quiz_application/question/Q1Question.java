package quiz_application.question;

import java.util.Scanner;

public class Q1Question extends ChoiceQuestion {
    private final String target;
    private final int wrongHint;

    public Q1Question(String question, int wrongHint, String[] hints, String target) {
        super(question, hints);
        this.target = target;
        this.wrongHint = wrongHint;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public double askAndGetScore(Scanner input) {
        System.err.println("\nquestion> " + this.question + "[allowed: 1-4 for the wrong hint]");

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
}
