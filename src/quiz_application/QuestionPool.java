package quiz_application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import quiz_application.question.Question;

public class QuestionPool {
    private ArrayList<Question> questions = new ArrayList<>();

    public QuestionPool(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public QuestionPool(Question... questions) {
        this.questions.addAll(Arrays.asList(questions));
    }

    public boolean addQuestion(Question q) {
        if (q == null) return false;

        return this.questions.add(q);
    }

    public double startQuiz() {
        double result = 0;

        Collections.shuffle(questions);

        Scanner input = new Scanner(System.in);
        for (Question q : questions) {
            result += q.askAndGetScore(input);
        }

        return result;
    }

    public void printAllQuestionsAndAnswers() {
        for (Question q : questions) {
            q.printQuestionAndAnswer();
        }
    }

}
