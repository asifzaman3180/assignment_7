import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int numberOfStudents = scanner.nextInt();

        String[] studentNames = new String[numberOfStudents];
        int[] studentIds = new int[numberOfStudents];
        int[] subject1Marks = new int[numberOfStudents];
        int[] subject2Marks = new int[numberOfStudents];
        int[] subject3Marks = new int[numberOfStudents];
        double[] averageMarks = new double[numberOfStudents];

        double highestAverage = 0;
        int topperIndex = 0;

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Enter name:");
            studentNames[i] = scanner.next();

            System.out.println("Enter ID:");
            studentIds[i] = scanner.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            subject1Marks[i] = scanner.nextInt();
            subject2Marks[i] = scanner.nextInt();
            subject3Marks[i] = scanner.nextInt();

            averageMarks[i] = (subject1Marks[i] + subject2Marks[i] + subject3Marks[i]) / 3.0;

            if (averageMarks[i] > highestAverage) {
                highestAverage = averageMarks[i];
                topperIndex = i;
            }
        }

        System.out.println("All Students:");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Name: " + studentNames[i] + " | ID: " + studentIds[i] + " | Average: " + averageMarks[i]);
        }

        System.out.println("Topper: " + studentNames[topperIndex] + " | Average: " + averageMarks[topperIndex]);

        System.out.println("Sort by Average? (y/n)");
        String sortChoice = scanner.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numberOfStudents - 1; i++) {
                for (int j = i + 1; j < numberOfStudents; j++) {
                    if (averageMarks[i] < averageMarks[j]) {

                        String tempName = studentNames[i];
                        studentNames[i] = studentNames[j];
                        studentNames[j] = tempName;

                        int tempId = studentIds[i];
                        studentIds[i] = studentIds[j];
                        studentIds[j] = tempId;

                        int tempMark1 = subject1Marks[i];
                        subject1Marks[i] = subject1Marks[j];
                        subject1Marks[j] = tempMark1;

                        int tempMark2 = subject2Marks[i];
                        subject2Marks[i] = subject2Marks[j];
                        subject2Marks[j] = tempMark2;

                        int tempMark3 = subject3Marks[i];
                        subject3Marks[i] = subject3Marks[j];
                        subject3Marks[j] = tempMark3;

                        double tempAverage = averageMarks[i];
                        averageMarks[i] = averageMarks[j];
                        averageMarks[j] = tempAverage;
                    }
                }
            }

            System.out.println("Sorted List (by Average):");
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.println(studentNames[i] + " | Average: " + averageMarks[i]);
            }
        }

        System.out.println("Search student by ID? (y/n)");
        String searchChoice = scanner.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.println("Enter ID:");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < numberOfStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.println("Found: " + studentNames[i] + " | Average: " + averageMarks[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found!");
            }
        }

        System.out.println("Calculate grade? (y/n)");
        String gradeChoice = scanner.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numberOfStudents; i++) {
                String grade;
                if (averageMarks[i] >= 80) grade = "A+";
                else if (averageMarks[i] >= 70) grade = "A";
                else if (averageMarks[i] >= 60) grade = "B";
                else if (averageMarks[i] >= 50) grade = "C";
                else grade = "F";

                System.out.println(studentNames[i] + " | Grade: " + grade);
            }
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
