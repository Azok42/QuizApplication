package quiz_application;

import quiz_application.question.Question;

public class Quiz {
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

        Question q0 = new Question("The JVM (Java Virtual Machine) is platform-independent.", false);
        Question q1 = new Question("The String class in Java is immutable.", true);
        Question q2 = new Question("In Java, a class can inherit from multiple classes using the extends keyword.", false);
        Question q3 = new Question("The finalize() method is guaranteed to be called by the Garbage Collector before an object is destroyed.", false);
        Question q4 = new Question("An Interface can contain both abstract methods and default methods.", true);
        Question q5 = new Question("The == operator is used to compare the content/values of two String objects.", false);
        Question q6 = new Question("Java is a 100% pure object-oriented programming language.", false);
        Question q7 = new Question("A static method can access non-static instance variables directly.", false);
        Question q8 = new Question("The finally block in a try-catch-finally statement always executes, even if an exception is thrown.", true);
        Question q9 = new Question("Local variables in Java are initialized with default values (like 0 or null) if not explicitly assigned.", false);

        QuestionPool qp = new QuestionPool(q1, q2, q3, q4, q5, q6, q7, q8, q9, q0);

        if (printMode) {
            qp.printAllQuestionsAndAnswers();
            return;
        }

        int result = qp.startQuiz();
        System.out.println(result);
    }

    public static void showUsage() {
        System.out.println("USAGE: program [print]");
    }
}
