package quiz_application.question;

import java.util.Scanner;

public interface Question {
    public double askAndGetScore(Scanner input);
    public void printQuestionAndAnswer();
}
