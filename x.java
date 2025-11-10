import java.util.*;

public class StudentApp {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int numStudents = sc.nextInt();

        String names[] = new String[numStudents];
        int ids[] = new int[numStudents];
        int marks1[] = new int[numStudents];
        int marks2[] = new int[numStudents];
        int marks3[] = new int[numStudents];
        double averages[] = new double[numStudents];

        double topAverage = 0;
        int topIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name:");
            names[i] = sc.next();

            System.out.println("Enter id:");
            ids[i] = sc.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            marks1[i] = sc.nextInt();
            marks2[i] = sc.nextInt();
            marks3[i] = sc.nextInt();

            averages[i] = (marks1[i] + marks2[i] + marks3[i]) / 3.0;

            if (averages[i] > topAverage) {
                topAverage = averages[i];
                topIndex = i;
            }
        }

        System.out.println("\nAll Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + names[i] + " | Id: " + ids[i] + " | Avg: " + averages[i]);
        }

        System.out.println("\nTopper: " + names[topIndex] + " | Avg: " + averages[topIndex]);

        System.out.println("\nSort by Average? (y/n)");
        String sortChoice = sc.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numStudents - 1; i++) {
                for (int j = i + 1; j < numStudents; j++) {
                    if (averages[i] < averages[j]) {
                        // swap names
                        String tempName = names[i];
                        names[i] = names[j];
                        names[j] = tempName;

                        // swap ids
                        int tempId = ids[i];
                        ids[i] = ids[j];
                        ids[j] = tempId;

                        // swap marks
                        int tempM1 = marks1[i];
                        marks1[i] = marks1[j];
                        marks1[j] = tempM1;

                        int tempM2 = marks2[i];
                        marks2[i] = marks2[j];
                        marks2[j] = tempM2;

                        int tempM3 = marks3[i];
                        marks3[i] = marks3[j];
                        marks3[j] = tempM3;

                        // swap averages
                        double tempAvg = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List:");
            for (int i = 0; i < numStudents; i++) {
                System.out.println(names[i] + " | Avg: " + averages[i]);
            }
        }

        System.out.println("\nSearch student by id? (y/n)");
        String searchChoice = sc.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.println("Enter id:");
            int searchId = sc.nextInt();
            boolean found = false;

            for (int i = 0; i < numStudents; i++) {
                if (ids[i] == searchId) {
                    System.out.println("Found: " + names[i] + " | Avg: " + averages[i]);
                    found = true;
                }
            }

            if (!found)
                System.out.println("Not found");
        }

        System.out.println("\nCalculate grade? (y/n)");
        String gradeChoice = sc.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numStudents; i++) {
                String grade = "";

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
        sc.close();
    }
}
