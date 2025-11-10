import java.util.*;

public class StudentRecord {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int numStudents = input.nextInt();

        String[] studentNames = new String[numStudents];
        int[] studentIds = new int[numStudents];
        int[] subject1Marks = new int[numStudents];
        int[] subject2Marks = new int[numStudents];
        int[] subject3Marks = new int[numStudents];
        double[] averages = new double[numStudents];

        int topperIndex = inputStudentData(input, numStudents, studentNames, studentIds,
                                           subject1Marks, subject2Marks, subject3Marks, averages);

        displayAllStudents(numStudents, studentNames, studentIds, averages);
        displayTopper(studentNames, averages, topperIndex);

        System.out.println("Sort by Average? y/n");
        String sortChoice = input.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            sortByAverage(numStudents, studentNames, studentIds, subject1Marks, subject2Marks, subject3Marks, averages);
            displaySortedList(numStudents, studentNames, averages);
        }

        System.out.println("Search student by ID?");
        String searchChoice = input.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            searchStudentById(input, numStudents, studentNames, studentIds, averages);
        }

        System.out.println("Calculate grade?");
        String gradeChoice = input.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            calculateGrades(numStudents, studentNames, averages);
        }

        System.out.println("Bye!");
    }


    public static int inputStudentData(Scanner input, int numStudents, String[] names, int[] ids,
                                       int[] marks1, int[] marks2, int[] marks3, double[] averages) {
        double highestAverage = 0;
        int topperIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name:");
            names[i] = input.next();

            System.out.println("Enter ID:");
            ids[i] = input.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            marks1[i] = input.nextInt();
            marks2[i] = input.nextInt();
            marks3[i] = input.nextInt();

            averages[i] = (marks1[i] + marks2[i] + marks3[i]) / 3.0;

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }
        return topperIndex;
    }

    public static void displayAllStudents(int numStudents, String[] names, int[] ids, double[] averages) {
        System.out.println("All Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Average: " + averages[i]);
        }
    }

    
    public static void displayTopper(String[] names, double[] averages, int topperIndex) {
        System.out.println("Topper: " + names[topperIndex] + " | Average: " + averages[topperIndex]);
    }


    public static void sortByAverage(int numStudents, String[] names, int[] ids,
                                     int[] marks1, int[] marks2, int[] marks3, double[] averages) {
        for (int i = 0; i < numStudents - 1; i++) {
            for (int j = i + 1; j < numStudents; j++) {
                if (averages[i] < averages[j]) {
                    swap(names, i, j);
                    swap(ids, i, j);
                    swap(marks1, i, j);
                    swap(marks2, i, j);
                    swap(marks3, i, j);
                    swap(averages, i, j);
                }
            }
        }
    }


    public static void displaySortedList(int numStudents, String[] names, double[] averages) {
        System.out.println("Sorted List:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println(names[i] + " " + averages[i]);
        }
    }


    public static void searchStudentById(Scanner input, int numStudents, String[] names, int[] ids, double[] averages) {
        System.out.println("Enter ID:");
        int searchId = input.nextInt();
        boolean found = false;

        for (int i = 0; i < numStudents; i++) {
            if (ids[i] == searchId) {
                System.out.println("Found: " + names[i] + " | Average: " + averages[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Not found");
        }
    }

   
    public static void calculateGrades(int numStudents, String[] names, double[] averages) {
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
            System.out.println(names[i] + " | Grade: " + grade);
        }
    }

    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
