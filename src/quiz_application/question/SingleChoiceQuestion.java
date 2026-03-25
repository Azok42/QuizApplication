package quiz_application.question;

import java.util.Scanner;

public class SingleChoiceQuestion extends ChoiceQuestion {
    private final int correctAnswer;

    public SingleChoiceQuestion(String question, int correctAnswer, String[] answers) {
        super(question, answers);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public double askAndGetScore(Scanner input) {
        System.out.println("question> " + this.question + " [allowed: 1-4]");

        printAnswers();
        
        int userInput = getUserInput(input);

        return (userInput == this.correctAnswer) ? 1 : 0;
    }

    @Override
    public void printQuestionAndAnswer() {
        System.out.println("question: " + this.question + "\n answer: " + this.answers[this.correctAnswer-1]);
    }

}
