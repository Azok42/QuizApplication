package quiz_application;

import java.util.Scanner;

public class Quiz {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        boolean printMode = false;

        if (args.length > 1) {
            showUsage();
            return;
        } else if (args.length == 1) {
            printMode = args[0].equals("print");
            if (!printMode) {
                showUsage();
                return;
            }
        }

        Scanner input = new Scanner(System.in);

        String pathInput = "";
        try {
            System.out.println("Specify the path of the question pool file (leave empty for default)");
            pathInput = input.nextLine();

            if (pathInput.isEmpty())
                pathInput = "../javaQuestions.json";

        } catch (Exception e) {
            e.printStackTrace();
        }

        QuestionPool qp = new QuestionPool(pathInput);

        if (printMode) {
            qp.printAllQuestionsAndAnswers();
            return;
        }

        double result = qp.startQuiz(input);
        System.out.println(result);
    }

    public static void showUsage() {
        System.out.println("USAGE: program [print]");
    }
}
