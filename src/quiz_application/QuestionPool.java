package quiz_application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

    public boolean loadQuestions(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;

                if (line.isBlank())
                    continue;

                switch (line) {
                    case "TrueFalseQuestion" -> this.addQuestion(new TrueFalseQuestion(reader));

                    case "SingleChoiceQuestion" -> this.addQuestion(new SingleChoiceQuestion(reader));

                    case "MultipleChoiceQuestion" -> this.addQuestion(new MultipleChoiceQuestion(reader));

                    case "Q1Question" -> this.addQuestion(new Q1Question(reader));

                    default -> throw new IOException("Error in file: unknown question type: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return false;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean saveQuestions(String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (Question q : this.questions) {
                if (!q.save(writer))
                    return false;
            }
        } catch (IOException e) {
            System.err.println("File not found");
            return false;
        }

        return true;
    }

    
    @SuppressWarnings("CallToPrintStackTrace")
    public void loadPoolFile(String pathToFile) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray questions = new JSONArray(content);

        for (int i = 0; i < questions.length(); i++) {
            JSONObject obj = questions.getJSONObject(i);

            String questionText = obj.getString("question");
            String questionType = obj.getString("type");

            switch (questionType) {
                case "mult" -> {
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
                }
                case "single" -> {
                    JSONArray answersJson = obj.getJSONArray("answers");
                    String[] answersArray = new String[answersJson.length()];
                    for (int j = 0; j < answersJson.length(); j++) {
                        answersArray[j] = answersJson.getString(j);
                    }
                    int correctAnswer = obj.getInt("correctAnswer");
                    this.questions.add(new SingleChoiceQuestion(questionText, correctAnswer, answersArray));
                }
                case "tf" -> {
                    boolean correctAnswer = obj.getBoolean("correctAnswer");
                    this.questions.add(new TrueFalseQuestion(questionText, correctAnswer));
                }
                case "q1" -> {
                    JSONArray hintsJson = obj.getJSONArray("hints");
                    String[] hintsArray = new String[hintsJson.length()];
                    for (int j = 0; j < hintsJson.length(); j++) {
                        hintsArray[j] = hintsJson.getString(j);
                    }
                    String target = obj.getString("target");
                    int correctAnswer = obj.getInt("wrongHint");
                    this.questions.add(new Q1Question(questionText, correctAnswer, hintsArray, target));
                }
                default -> {
                }
            }
        }
    }

    public boolean addQuestion(Question q) {
        if (q == null)
            return false;

        return this.questions.add(q);
    }

    public double startQuiz() {
        printBanner();

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
            System.out.println("");
        }
    }

    public static void printBanner() {
        System.out.print("\033[H\033[2J");
        System.out.println(
                "  ██████╗ ██╗   ██╗██╗███████╗     █████╗ ██████╗ ██████╗ ██╗     ██╗ ██████╗ █████╗ ████████╗██╗ ██████╗ ███╗   ██╗\n"
                        +
                        " ██╔═══██╗██║   ██║██║╚══███╔╝    ██╔══██╗██╔══██╗██╔══██╗██║     ██║██╔════╝██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║\n"
                        +
                        " ██║   ██║██║   ██║██║  ███╔╝     ███████║██████╔╝██████╔╝██║     ██║██║     ███████║   ██║   ██║██║   ██║██╔██╗ ██║\n"
                        +
                        " ██║▄▄ ██║██║   ██║██║ ███╔╝      ██╔══██║██╔═══╝ ██╔═══╝ ██║     ██║██║     ██╔══██║   ██║   ██║██║   ██║██║╚██╗██║\n"
                        +
                        " ╚██████╔╝╚██████╔╝██║███████╗    ██║  ██║██║     ██║     ███████╗██║╚██████╗██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║\n"
                        +
                        "  ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝    ╚═╝  ╚═╝╚═╝     ╚═╝     ╚══════╝╚═╝ ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝");
    }
}
