import java.util.*;

public class StudentApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numberOfStudents = scanner.nextInt();

        String[] studentNames = new String[numberOfStudents];
        int[] studentIds = new int[numberOfStudents];
        int[] subject1Marks = new int[numberOfStudents];
        int[] subject2Marks = new int[numberOfStudents];
        int[] subject3Marks = new int[numberOfStudents];
        double[] averageMarks = new double[numberOfStudents];

        inputStudentData(scanner, numberOfStudents, studentNames, studentIds, subject1Marks, subject2Marks, subject3Marks, averageMarks);

        displayAllStudents(numberOfStudents, studentNames, studentIds, averageMarks);

        int topperIndex = findTopper(numberOfStudents, averageMarks);
        System.out.println("\nTopper: " + studentNames[topperIndex] + " | Average: " + averageMarks[topperIndex]);

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = scanner.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            sortByAverage(numberOfStudents, studentNames, studentIds, subject1Marks, subject2Marks, subject3Marks, averageMarks);
            System.out.println("\nSorted List by Average:");
            displayAllStudents(numberOfStudents, studentNames, studentIds, averageMarks);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            searchById(numberOfStudents, studentNames, studentIds, averageMarks, searchId);
        }

        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            printGrades(numberOfStudents, studentNames, averageMarks);
        }

        System.out.println("\nBye!");
        scanner.close();
    }

    // ------------------------------------------------------------------------
    // 📘 Input method
    private static void inputStudentData(Scanner scanner, int numberOfStudents,
                                         String[] studentNames, int[] studentIds,
                                         int[] subject1Marks, int[] subject2Marks, int[] subject3Marks,
                                         double[] averageMarks) {
        double highestAverage = 0;

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
