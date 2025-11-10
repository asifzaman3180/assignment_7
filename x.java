import java.util.*;

public class StudentManager {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int numStudents = scanner.nextInt();

        String[] names = new String[numStudents];
        int[] ids = new int[numStudents];
        int[] subject1 = new int[numStudents];
        int[] subject2 = new int[numStudents];
        int[] subject3 = new int[numStudents];
        double[] averages = new double[numStudents];

        double highestAverage = 0;
        int topperIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name:");
            names[i] = scanner.next();

            System.out.println("Enter ID:");
            ids[i] = scanner.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            subject1[i] = scanner.nextInt();
            subject2[i] = scanner.nextInt();
            subject3[i] = scanner.nextInt();

            averages[i] = (subject1[i] + subject2[i] + subject3[i]) / 3.0;

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }

        System.out.println("\nAll Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Average: " + averages[i]);
        }

        System.out.println("\nTopper: " + names[topperIndex] + " | Average: " + averages[topperIndex]);

        System.out.println("\nSort by Average? (y/n)");
        String sortChoice = scanner.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numStudents - 1; i++) {
                for (int j = i + 1; j < numStudents; j++) {
                    if (averages[i] < averages[j]) {
                        // Swap names
                        String tempName = names[i];
                        names[i] = names[j];
                        names[j] = tempName;

                        // Swap IDs
                        int tempId = ids[i];
                        ids[i] = ids[j];
                        ids[j] = tempId;

                        // Swap marks
                        int tempSub1 = subject1[i];
                        subject1[i] = subject1[j];
                        subject1[j] = tempSub1;

                        int tempSub2 = subject2[i];
                        subject2[i] = subject2[j];
                        subject2[j] = tempSub2;

                        int tempSub3 = subject3[i];
                        subject3[i] = subject3[j];
                        subject3[j] = tempSub3;

                        // Swap averages
                        double tempAvg = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < numStudents; i++) {
                System.out.println(names[i] + " | Average: " + averages[i]);
            }
        }

        System.out.println("\nSearch student by ID? (y/n)");
        String searchChoice = scanner.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.println("Enter ID:");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < numStudents; i++) {
                if (ids[i] == searchId) {
                    System.out.println("Found: " + names[i] + " | Average: " + averages[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }
        }

        System.out.println("\nCalculate grade for all students? (y/n)");
        String gradeChoice = scanner.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numStudents; i++) {
                String grade;

                if (averages[i] >= 80)
                    grade = "A+";
                else if (averages[i] >= 70)
                    grade = "A";
                else if (averages[i] >= 60)
                    grade = "B";
                else if (averages[i] >= 50)
                    grade = "C";
                else
                    grade = "F";

                System.out.println(names[i] + " | Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
