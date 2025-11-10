import java.util.*;

public class StudentApp {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = sc.nextInt();

        String names[] = new String[numStudents];
        int ids[] = new int[numStudents];
        int marks1[] = new int[numStudents];
        int marks2[] = new int[numStudents];
        int marks3[] = new int[numStudents];
        double averages[] = new double[numStudents];

        inputStudents(sc, numStudents, names, ids, marks1, marks2, marks3, averages);
        printAllStudents(numStudents, names, ids, averages);

        int topperIndex = findTopper(averages);
        System.out.println("\nTopper: " + names[topperIndex] + " | Avg: " + averages[topperIndex]);

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = sc.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            sortByAverage(numStudents, names, ids, marks1, marks2, marks3, averages);
            printSortedList(numStudents, names, averages);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = sc.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            searchById(sc, numStudents, names, ids, averages);
        }

        System.out.print("\nCalculate Grades? (y/n): ");
        String gradeChoice = sc.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            printGrades(numStudents, names, averages);
        }

        System.out.println("\nBye!");
        sc.close();
    }


    static void inputStudents(Scanner sc, int numStudents, String names[], int ids[],
                              int marks1[], int marks2[], int marks3[], double averages[]) {
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            names[i] = sc.next();
            System.out.print("ID: ");
            ids[i] = sc.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            marks1[i] = sc.nextInt();
            marks2[i] = sc.nextInt();
            marks3[i] = sc.nextInt();

            averages[i] = calculateAverage(marks1[i], marks2[i], marks3[i]);
        }
    }

    static double calculateAverage(int m1, int m2, int m3) {
        return (m1 + m2 + m3) / 3.0;
    }

    static int findTopper(double averages[]) {
        double topAverage = averages[0];
        int topIndex = 0;
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > topAverage) {
                topAverage = averages[i];
                topIndex = i;
            }
        }
        return topIndex;
    }

    static void sortByAverage(int numStudents, String names[], int ids[],
                              int marks1[], int marks2[], int marks3[], double averages[]) {
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
                    int tempM1 = marks1[i];
                    marks1[i] = marks1[j];
                    marks1[j] = tempM1;

                    int tempM2 = marks2[i];
                    marks2[i] = marks2[j];
                    marks2[j] = tempM2;

                    int tempM3 = marks3[i];
                    marks3[i] = marks3[j];
                    marks3[j] = tempM3;

                    // Swap averages
                    double tempAvg = averages[i];
                    averages[i] = averages[j];
                    averages[j] = tempAvg;
                }
            }
        }
    }

    static void searchById(Scanner sc, int numStudents, String names[], int ids[], double averages[]) {
        System.out.print("Enter ID: ");
        int searchId = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < numStudents; i++) {
            if (ids[i] == searchId) {
                System.out.println("Found: " + names[i] + " | Avg: " + averages[i]);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student not found.");
    }

    static void printGrades(int numStudents, String names[], double averages[]) {
        System.out.println("\nGrades:");
        for (int i = 0; i < numStudents; i++) {
            String grade;
            if (averages[i] >= 80) grade = "A+";
            else if (averages[i] >= 70) grade = "A";
            else if (averages[i] >= 60) grade = "B";
            else if (averages[i] >= 50) grade = "C";
            else grade = "F";

            System.out.println(names[i] + " | Grade: " + grade);
        }
    }

    static void printAllStudents(int numStudents, String names[], int ids[], double averages[]) {
        System.out.println("\nAll Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Avg: " + averages[i]);
        }
    }

    static void printSortedList(int numStudents, String names[], double averages[]) {
        System.out.println("\nSorted List:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println(names[i] + " | Avg: " + averages[i]);
        }
    }
}
