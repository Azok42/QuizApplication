package quiz_application.question;

import java.io.PrintWriter;
import java.util.Scanner;

public interface Question {
    public double askAndGetScore(Scanner input, int number);
    public void printQuestionAndAnswer(int number);
    public boolean save(PrintWriter writer);
    public String getAnswerOverview();
}
