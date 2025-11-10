import java.util.*;

public class StudentRecord {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of students: ");
        int numStudents = input.nextInt();

        String[] studentNames = new String[numStudents];
        int[] studentIds = new int[numStudents];
        int[] subject1Marks = new int[numStudents];
        int[] subject2Marks = new int[numStudents];
        int[] subject3Marks = new int[numStudents];
        double[] averages = new double[numStudents];

        double highestAverage = 0;
        int topperIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name:");
            studentNames[i] = input.next();

            System.out.println("Enter ID:");
            studentIds[i] = input.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            subject1Marks[i] = input.nextInt();
            subject2Marks[i] = input.nextInt();
            subject3Marks[i] = input.nextInt();

            averages[i] = (subject1Marks[i] + subject2Marks[i] + subject3Marks[i]) / 3.0;

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }

        System.out.println("All Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + studentNames[i] + " | ID: " + studentIds[i] + " | Average: " + averages[i]);
        }

        System.out.println("Topper: " + studentNames[topperIndex] + " | Average: " + averages[topperIndex]);

        System.out.println("Sort by Average? y/n");
        String sortChoice = input.next();

        if (sortChoice.equals("y")) {
            for (int i = 0; i < numStudents - 1; i++) {
                for (int j = i + 1; j < numStudents; j++) {
                    if (averages[i] < averages[j]) {

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

                        double tempAverage = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAverage;
                    }
                }
            }

            System.out.println("Sorted List:");
            for (int i = 0; i < numStudents; i++) {
                System.out.println(studentNames[i] + " " + averages[i]);
            }
        }

        System.out.println("Search student by ID?");
        String searchChoice = input.next();

        if (searchChoice.equals("y")) {
            System.out.println("Enter ID:");
            int searchId = input.nextInt();
            boolean found = false;

            for (int i = 0; i < numStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.println("Found: " + studentNames[i] + " | Average: " + averages[i]);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Not found");
            }
        }

        System.out.println("Calculate grade?");
        String gradeChoice = input.next();

        if (gradeChoice.equals("y")) {
            for (int i = 0; i < numStudents; i++) {
                String grade;
                if (averages[i] >= 80) {
                    grade = "A+";
                } else if (averages[i] >= 70) {
                    grade = "A";
                } else if (averages[i] >= 60) {
                    grade = "B";
                } else if (averages[i] >= 50) {
                    grade = "C";
                } else {
                    grade = "F";
                }
                System.out.println(studentNames[i] + " | Grade: " + grade);
            }
        }

        System.out.println("Bye!");
    }
}
