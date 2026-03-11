package quiz_application;

import quiz_application.question.Question;
import quiz_application.question.SingleChoiceQuestion;
import quiz_application.question.TrueFalseQuestion;

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

        Question q0 = new SingleChoiceQuestion(
				"Which access modifier allows a member to be accessed only within its own package and by subclasses in other packages?",
				1,
				new String[] {"protected", "private", "no modifier", "public"}
				);

		Question q1 = new SingleChoiceQuestion(
				"What is the result of attempting to compile and run a Java program where a local variable is used before it is initialized?",
				2,
				new String[] {
					"a runtime exception is thrown", 
					"a compile-time error occurs", 
					"The variable is assigned a random memory value",
					"The program compiles but prints 'null' or '0'"
				}
				);

		Question q2 = new SingleChoiceQuestion(
				"Which of these memory areas in the JVM is responsible for storing objects and their instance variables?",
				3,
				new String[] {"Stack", "Method Area", "Heap", "PC Register"}
				);


		Question q3 = new SingleChoiceQuestion(
				"Where are String literals (e.g., \"Hello\") stored in memory to allow for reusability?",
				4,
				new String[] {"The Stack", "The Garbage Collector", "The Native Method Stack", "The String Constant Pool"}
				);

		Question q4 = new SingleChoiceQuestion(
				"In Method Overloading, which of the following MUST be different for the compiler to distinguish between two methods?",
				1,
				new String[] {"The parameter list", "The return type", "The access modifier", "The names of the parameters"}
				);
        Question q5 = new TrueFalseQuestion("The == operator is used to compare the content/values of two String objects.", false);
        Question q6 = new TrueFalseQuestion("Java is a 100% pure object-oriented programming language.", false);
        Question q7 = new TrueFalseQuestion("A static method can access non-static instance variables directly.", false);
        Question q8 = new TrueFalseQuestion("The finally block in a try-catch-finally statement always executes, even if an exception is thrown.", true);
        Question q9 = new TrueFalseQuestion("Local variables in Java are initialized with default values (like 0 or null) if not explicitly assigned.", false);

        QuestionPool qp = new QuestionPool(q0, q1, q2, q3, q4, q5, q6, q7, q8, q9);

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
