import java.util.*;

public class StudentManagement {
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

        double highestAverage = 0;
        int topperIndex = 0;

        // Input student details
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");

            System.out.print("Enter name: ");
            studentNames[i] = scanner.next();

            System.out.print("Enter ID: ");
            studentIds[i] = scanner.nextInt();

            System.out.print("Enter marks of 3 subjects: ");
            subject1Marks[i] = scanner.nextInt();
            subject2Marks[i] = scanner.nextInt();
            subject3Marks[i] = scanner.nextInt();

            averageMarks[i] = (subject1Marks[i] + subject2Marks[i] + subject3Marks[i]) / 3.0;

            if (averageMarks[i] > highestAverage) {
                highestAverage = averageMarks[i];
                topperIndex = i;
            }
        }

        // Display all students
        System.out.println("\nAll Students:");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Name: " + studentNames[i] +
                               " | ID: " + studentIds[i] +
                               " | Average: " + averageMarks[i]);
        }

        // Display topper
        System.out.println("\nTopper: " + studentNames[topperIndex] +
                           " | Average: " + averageMarks[topperIndex]);

        // Sorting option
        System.out.print("\nSort by average? (y/n): ");
        String sortChoice = scanner.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numberOfStudents - 1; i++) {
                for (int j = i + 1; j < numberOfStudents; j++) {
                    if (averageMarks[i] < averageMarks[j]) {
                        // Swap all related data
                        String tempName = studentNames[i];
                        studentNames[i] = studentNames[j];
                        studentNames[j] = tempName;

                        int tempId = studentIds[i];
                        studentIds[i] = studentIds[j];
                        studentIds[j] = tempId;

                        int tempM1 = subject1Marks[i];
                        subject1Marks[i] = subject1Marks[j];
                        subject1Marks[j] = tempM1;

                        int tempM2 = subject2Marks[i];
                        subject2Marks[i] = subject2Marks[j];
                        subject2Marks[j] = tempM2;

                        int tempM3 = subject3Marks[i];
                        subject3Marks[i] = subject3Marks[j];
                        subject3Marks[j] = tempM3;

                        double tempAvg = averageMarks[i];
                        averageMarks[i] = averageMarks[j];
                        averageMarks[j] = tempAvg;
                    }
                }
            }

            // Display sorted list
            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.println(studentNames[i] + " | Average: " + averageMarks[i]);
            }
        }

        // Search by ID option
        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID to search: ");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < numberOfStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.println("Found: " + studentNames[i] +
                                       " | Average: " + averageMarks[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }
        }

        // Grade calculation option
        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numberOfStudents; i++) {
                String grade;
                if (averageMarks[i] >= 80)
                    grade = "A+";
                else if (averageMarks[i] >= 70)
                    grade = "A";
                else if (averageMarks[i] >= 60)
                    grade = "B";
                else if (averageMarks[i] >= 50)
                    grade = "C";
                else
                    grade = "F";

                System.out.println(studentNames[i] + " | Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
