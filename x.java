import java.util.*;

public class StudentApp {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int numStudents = scanner.nextInt();

        String[] studentNames = new String[numStudents];
        int[] studentIds = new int[numStudents];
        int[] marksSubject1 = new int[numStudents];
        int[] marksSubject2 = new int[numStudents];
        int[] marksSubject3 = new int[numStudents];
        double[] averages = new double[numStudents];

        double highestAverage = 0;
        int topperIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name:");
            studentNames[i] = scanner.next();

            System.out.println("Enter ID:");
            studentIds[i] = scanner.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            marksSubject1[i] = scanner.nextInt();
            marksSubject2[i] = scanner.nextInt();
            marksSubject3[i] = scanner.nextInt();

            averages[i] = (marksSubject1[i] + marksSubject2[i] + marksSubject3[i]) / 3.0;

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }

        System.out.println("\nAll Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + studentNames[i] + " ID: " + studentIds[i] + " Avg: " + averages[i]);
        }

        System.out.println("\nTopper: " + studentNames[topperIndex] + " Avg: " + averages[topperIndex]);

        System.out.println("\nSort by Average? y/n");
        String sortChoice = scanner.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numStudents - 1; i++) {
                for (int j = i + 1; j < numStudents; j++) {
                    if (averages[i] < averages[j]) {
                        // Swap names
                        String tempName = studentNames[i];
                        studentNames[i] = studentNames[j];
                        studentNames[j] = tempName;

                        // Swap IDs
                        int tempId = studentIds[i];
                        studentIds[i] = studentIds[j];
                        studentIds[j] = tempId;

                        // Swap marks
                        int tempM1 = marksSubject1[i];
                        marksSubject1[i] = marksSubject1[j];
                        marksSubject1[j] = tempM1;

                        int tempM2 = marksSubject2[i];
                        marksSubject2[i] = marksSubject2[j];
                        marksSubject2[j] = tempM2;

                        int tempM3 = marksSubject3[i];
                        marksSubject3[i] = marksSubject3[j];
                        marksSubject3[j] = tempM3;

                        // Swap averages
                        double tempAvg = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List:");
            for (int i = 0; i < numStudents; i++) {
                System.out.println(studentNames[i] + " Avg: " + averages[i]);
            }
        }

        System.out.println("\nSearch student by ID? y/n");
        String searchChoice = scanner.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.println("Enter ID:");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < numStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.println("Found: " + studentNames[i] + " Avg: " + averages[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Not found");
            }
        }

        System.out.println("\nCalculate grade? y/n");
        String gradeChoice = scanner.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numStudents; i++) {
                String grade;
                if (averages[i] >= 80) grade = "A+";
                else if (averages[i] >= 70) grade = "A";
                else if (averages[i] >= 60) grade = "B";
                else if (averages[i] >= 50) grade = "C";
                else grade = "F";

                System.out.println(studentNames[i] + " Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
    }
}
