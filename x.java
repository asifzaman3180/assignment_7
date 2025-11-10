import java.util.*;

public class StudentRecords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int noOfStudents = scanner.nextInt();

        String[] name = new String[noOfStudents];
        int[] studentId = new int[noOfStudents];
        int[] mark1 = new int[noOfStudents];
        int[] mark2 = new int[noOfStudents];
        int[] mark3 = new int[noOfStudents];
        double[] avg = new double[noOfStudents];

        double top = 0;
        int topIndex = 0;

        // Input data
        for (int i = 0; i < noOfStudents; i++) {
            System.out.print("Enter name: ");
            name[i] = scanner.next();

            System.out.print("Enter ID: ");
            studentId[i] = scanner.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            mark1[i] = scanner.nextInt();
            mark2[i] = scanner.nextInt();
            mark3[i] = scanner.nextInt();

            avg[i] = (mark1[i] + mark2[i] + mark3[i]) / 3.0;

            if (avg[i] > top) {
                top = avg[i];
                topIndex = i;
            }
        }

        // Display all students
        System.out.println("\nAll Students:");
        for (int i = 0; i < noOfStudents; i++) {
            System.out.println("Name: " + name[i] + 
                               " | ID: " + studentId[i] + 
                               " | Average: " + avg[i]);
        }

        // Topper
        System.out.println("\nTopper: " + name[topIndex] + 
                           " | Average: " + avg[topIndex]);

        // Sort by average
        System.out.print("\nSort by Average? (y/n): ");
        String isSorted = scanner.next();

        if (isSorted.equalsIgnoreCase("y")) {
            for (int i = 0; i < noOfStudents - 1; i++) {
                for (int j = i + 1; j < noOfStudents; j++) {
                    if (avg[i] < avg[j]) {
                        // swap everything
                        String tempName = name[i];
                        name[i] = name[j];
                        name[j] = tempName;

                        int tempId = studentId[i];
                        studentId[i] = studentId[j];
                        studentId[j] = tempId;

                        int tempM1 = mark1[i];
                        mark1[i] = mark1[j];
                        mark1[j] = tempM1;

                        int tempM2 = mark2[i];
                        mark2[i] = mark2[j];
                        mark2[j] = tempM2;

                        int tempM3 = mark3[i];
                        mark3[i] = mark3[j];
                        mark3[j] = tempM3;

                        double tempAvg = avg[i];
                        avg[i] = avg[j];
                        avg[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < noOfStudents; i++) {
                System.out.println("Name: " + name[i] + " | Avg: " + avg[i]);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by ID? (y/n): ");
        String isSearching = scanner.next();

        if (isSearching.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchedId = scanner.nextInt();
            boolean isFound = false;

            for (int i = 0; i < noOfStudents; i++) {
                if (studentId[i] == searchedId) {
                    System.out.println("Found: " + name[i] + 
                                       " | Avg: " + avg[i]);
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Student not found!");
            }
        }

        // Grade calculation
        System.out.print("\nCalculate grades? (y/n): ");
        String calculateGrade = scanner.next();

        if (calculateGrade.equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            for (int i = 0; i < noOfStudents; i++) {
                String grade;
                if (avg[i] >= 80)
                    grade = "A+";
                else if (avg[i] >= 70)
                    grade = "A";
                else if (avg[i] >= 60)
                    grade = "B";
                else if (avg[i] >= 50)
                    grade = "C";
                else
                    grade = "F";

                System.out.println(name[i] + " | Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
