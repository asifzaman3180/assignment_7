import java.util.*;

public class StudentManager {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int totalStudents = input.nextInt();

        String[] studentNames = new String[totalStudents];
        int[] studentIds = new int[totalStudents];
        int[] subject1Marks = new int[totalStudents];
        int[] subject2Marks = new int[totalStudents];
        int[] subject3Marks = new int[totalStudents];
        double[] averages = new double[totalStudents];

        double topAverage = 0.0;
        int topIndex = 0;

        
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");

            System.out.print("Name: ");
            studentNames[i] = input.next();

            System.out.print("ID: ");
            studentIds[i] = input.nextInt();

            System.out.print("Enter marks of 3 subjects: ");
            subject1Marks[i] = input.nextInt();
            subject2Marks[i] = input.nextInt();
            subject3Marks[i] = input.nextInt();

            averages[i] = (subject1Marks[i] + subject2Marks[i] + subject3Marks[i]) / 3.0;

            if (averages[i] > topAverage) {
                topAverage = averages[i];
                topIndex = i;
            }
        }

        
        System.out.println("\nAll Students:");
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("Name: " + studentNames[i] + " | ID: " + studentIds[i] + " | Avg: " + averages[i]);
        }

        
        System.out.println("\nTopper: " + studentNames[topIndex] + " | Avg: " + averages[topIndex]);

        
        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = input.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < totalStudents - 1; i++) {
                for (int j = i + 1; j < totalStudents; j++) {
                    if (averages[i] < averages[j]) {
                        
                        String tempName = studentNames[i];
                        studentNames[i] = studentNames[j];
                        studentNames[j] = tempName;

                        int tempId = studentIds[i];
                        studentIds[i] = studentIds[j];
                        studentIds[j] = tempId;

                        int tempSub1 = subject1Marks[i];
                        subject1Marks[i] = subject1Marks[j];
                        subject1Marks[j] = tempSub1;

                        int tempSub2 = subject2Marks[i];
                        subject2Marks[i] = subject2Marks[j];
                        subject2Marks[j] = tempSub2;

                        int tempSub3 = subject3Marks[i];
                        subject3Marks[i] = subject3Marks[j];
                        subject3Marks[j] = tempSub3;

                        double tempAvg = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < totalStudents; i++) {
                System.out.println("Name: " + studentNames[i] + " | Avg: " + averages[i]);
            }
        }

       
        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = input.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter student ID: ");
            int searchId = input.nextInt();
            boolean found = false;

            for (int i = 0; i < totalStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.println("Found: " + studentNames[i] + " | Avg: " + averages[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found!");
            }
        }

        
        System.out.print("\nCalculate grade for all students? (y/n): ");
        String gradeChoice = input.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            for (int i = 0; i < totalStudents; i++) {
                String grade;
                if (averages[i] >= 80) grade = "A+";
                else if (averages[i] >= 70) grade = "A";
                else if (averages[i] >= 60) grade = "B";
                else if (averages[i] >= 50) grade = "C";
                else grade = "F";

                System.out.println(studentNames[i] + " | Grade: " + grade);
            }
        }

        System.out.println("\nThank you! Program Ended.");
        input.close();
    }
}
