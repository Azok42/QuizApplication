package quiz_application;

public class Quiz {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        String command;
        if (args.length == 0)
            command = "start";
        else
            command = args[0];

        QuestionPool qp = new QuestionPool();

        switch (command) {
            case "print" -> qp.printAllQuestionsAndAnswers();

            case "start" -> System.out.println("\nYour Score: " + qp.startQuiz());
            
            case "help" -> printUsage();

            case "save" -> {
                if (args.length < 2)
                    System.out.println("Error: Mode 'save' needs a filename");
                else
                    qp.saveQuestions(args[1]);
            }
            
            case "loadsave" -> {
                if (args.length < 3)
                    System.out.println("Error: Mode 'loadsave' needs 2 filenames");
                else {
                    qp.loadPoolFile(args[1]);
                    qp.saveQuestions(args[2]);
                }
            }

            case "load" -> {
                if (args.length < 2)
                    System.out.println("Error: Mode 'load' needs a filename");
                else {
                    qp.loadQuestions(args[1]);
                    System.out.println("\nYour Score: " + qp.startQuiz());
                }
            }

            case "loadprint" -> {
                if (args.length < 2)
                    System.out.println("Error: Mode 'loadprint' needs a filename");
                else {
                    qp.loadQuestions(args[1]);
                    qp.printAllQuestionsAndAnswers();
                }
            }

            default -> {
                System.out.println("Error: Unknown mode: '" + command + "'");
                printUsage();
            }
        }

    }

    public static void printUsage() {
        System.out.println("USAGE:");
        System.out.println("  print");
        System.out.println("  save <text-filename>");
        System.out.println("  load <text-filename>");
        System.out.println("  loadprint <text-filename>");
        System.out.println("  loadsave <json-filename> <text-filename>");
    }
}
