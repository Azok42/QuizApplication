package quiz_application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import quiz_application.question.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

public final class QuestionPool {
    private ArrayList<Question> questions = new ArrayList<>();

    public QuestionPool(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public QuestionPool(Question... questions) {
        this.questions.addAll(Arrays.asList(questions));
    }

    public QuestionPool(String pathToFile) {
        loadPoolFile(pathToFile);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void loadPoolFile(String pathToFile) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray questions = new JSONArray(content);
        
        for (int i = 0; i < questions.length(); i++) {
            JSONObject obj = questions.getJSONObject(i);

            String questionText = obj.getString("question");
            String questionType = obj.getString("type");

            if (questionType.equals("mult")) {
                JSONArray answersJson = obj.getJSONArray("answers");
                String[] answersArray = new String[answersJson.length()];
                for (int j = 0; j < answersJson.length(); j++) {
                    answersArray[j] = answersJson.getString(j);
                }

                JSONArray correctJson = obj.getJSONArray("correctAnswers");
                Integer[] correctArray = new Integer[correctJson.length()];
                for (int j = 0; j < correctJson.length(); j++) {
                    correctArray[j] = correctJson.getInt(j);
                }

                this.questions.add(new MultipleChoiceQuestion(questionText, correctArray, answersArray));
            } else if(questionType.equals("single")) {
                JSONArray answersJson = obj.getJSONArray("answers");
                String[] answersArray = new String[answersJson.length()];
                for (int j = 0; j < answersJson.length(); j++) {
                    answersArray[j] = answersJson.getString(j);
                }

                int correctAnswer = obj.getInt("correctAnswer");

                this.questions.add(new SingleChoiceQuestion(questionText, correctAnswer, answersArray));
            }
        }
    }

    public boolean addQuestion(Question q) {
        if (q == null)
            return false;

        return this.questions.add(q);
    }

    public double startQuiz() {
        Scanner input = new Scanner(System.in);

        return startQuiz(input);
    }

    public double startQuiz(Scanner input) {
        double result = 0;

        Collections.shuffle(questions);

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
