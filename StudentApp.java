import java.util.*;

public class StudentApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int totalStudents = scanner.nextInt();

        String[] studentNames = new String[totalStudents];
        int[] studentIds = new int[totalStudents];
        int[] subject1Marks = new int[totalStudents];
        int[] subject2Marks = new int[totalStudents];
        int[] subject3Marks = new int[totalStudents];
        double[] studentAverages = new double[totalStudents];

        inputStudentData(scanner, totalStudents, studentNames, studentIds, subject1Marks, subject2Marks, subject3Marks, studentAverages);

        displayStudents(totalStudents, studentNames, studentIds, studentAverages);

        int topperIndex = findTopper(totalStudents, studentAverages);
        System.out.println("Topper: " + studentNames[topperIndex] + " Average: " + studentAverages[topperIndex]);

        System.out.print("Sort by Average? (y/n): ");
        String sortChoice = scanner.next();

        if (sortChoice.equals("y")) {
            sortByAverage(totalStudents, studentNames, studentIds, subject1Marks, subject2Marks, subject3Marks, studentAverages);
            System.out.println("Sorted List:");
            displayStudents(totalStudents, studentNames, studentIds, studentAverages);
        }

        System.out.print("Search student by ID? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equals("y")) {
            searchById(scanner, totalStudents, studentNames, studentIds, studentAverages);
        }

        System.out.print("Calculate grade? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equals("y")) {
            printGrades(totalStudents, studentNames, studentAverages);
        }

        System.out.println("Bye!");
    }

    static void inputStudentData(Scanner scanner, int totalStudents, String[] names, int[] ids, int[] sub1, int[] sub2, int[] sub3, double[] avg) {
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("Enter name:");
            names[i] = scanner.next();
            System.out.println("Enter ID:");
            ids[i] = scanner.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            sub1[i] = scanner.nextInt();
            sub2[i] = scanner.nextInt();
            sub3[i] = scanner.nextInt();
            avg[i] = calculateAverage(sub1[i], sub2[i], sub3[i]);
        }
    }

    static double calculateAverage(int m1, int m2, int m3) {
        return (m1 + m2 + m3) / 3.0;
    }

    static int findTopper(int totalStudents, double[] avg) {
        int topperIndex = 0;
        double highestAverage = avg[0];
        for (int i = 1; i < totalStudents; i++) {
            if (avg[i] > highestAverage) {
                highestAverage = avg[i];
                topperIndex = i;
            }
        }
        return topperIndex;
    }

    static void sortByAverage(int totalStudents, String[] names, int[] ids, int[] sub1, int[] sub2, int[] sub3, double[] avg) {
        for (int i = 0; i < totalStudents - 1; i++) {
            for (int j = i + 1; j < totalStudents; j++) {
                if (avg[i] < avg[j]) {

                    String tempName = names[i];
                    names[i] = names[j];
                    names[j] = tempName;

                    int tempId = ids[i];
                    ids[i] = ids[j];
                    ids[j] = tempId;

                    int t1 = sub1[i];
                    sub1[i] = sub1[j];
                    sub1[j] = t1;

                    int t2 = sub2[i];
                    sub2[i] = sub2[j];
                    sub2[j] = t2;

                    int t3 = sub3[i];
                    sub3[i] = sub3[j];
                    sub3[j] = t3;

                    double tempAvg = avg[i];
                    avg[i] = avg[j];
                    avg[j] = tempAvg;
                }
            }
        }
    }

    static void searchById(Scanner scanner, int totalStudents, String[] names, int[] ids, double[] avg) {
        System.out.print("Enter ID: ");
        int searchId = scanner.nextInt();
        boolean found = false;
        for (int i = 0; i < totalStudents; i++) {
            if (ids[i] == searchId) {
                System.out.println("Found: " + names[i] + " Average: " + avg[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not found");
        }
    }

    static void printGrades(int totalStudents, String[] names, double[] avg) {
        for (int i = 0; i < totalStudents; i++) {
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

            System.out.println(names[i] + " Grade: " + grade);
        }
    }

    static void displayStudents(int totalStudents, String[] names, int[] ids, double[] avg) {
        System.out.println("All Students:");
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("Name: " + names[i] + " ID: " + ids[i] + " Average: " + avg[i]);
        }
    }
}

