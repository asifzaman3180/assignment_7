import java.util.*;

public class StudentApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int numStudents = scanner.nextInt();

        String[] studentNames = new String[numStudents];
        int[] studentIds = new int[numStudents];
        int[] marksSubject1 = new int[numStudents];
        int[] marksSubject2 = new int[numStudents];
        int[] marksSubject3 = new int[numStudents];
        double[] averages = new double[numStudents];

        calculateAverage(scanner, studentNames, studentIds, marksSubject1, marksSubject2, marksSubject3, averages);

        System.out.println("\nAll Students:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + studentNames[i] + " ID: " + studentIds[i] + " Avg: " + averages[i]);
        }

        int topperIndex = findTopper(averages);
        System.out.println("\nTopper: " + studentNames[topperIndex] + " Avg: " + averages[topperIndex]);

        System.out.println("\nSort by Average? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(studentNames, studentIds, marksSubject1, marksSubject2, marksSubject3, averages);
            System.out.println("\nSorted List:");
            for (int i = 0; i < numStudents; i++) {
                System.out.println(studentNames[i] + " Avg: " + averages[i]);
            }
        }

        System.out.println("\nSearch student by ID? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            searchById(scanner, studentNames, studentIds, averages);
        }

        System.out.println("\nCalculate grade? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            printGrades(studentNames, averages);
        }

        System.out.println("\nBye!");
    }

    static void calculateAverage(Scanner scanner, String[] names, int[] ids, int[] m1, int[] m2, int[] m3, double[] avg) {
        for (int i = 0; i < names.length; i++) {
            System.out.println("Enter name:");
            names[i] = scanner.next();
            System.out.println("Enter ID:");
            ids[i] = scanner.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            m1[i] = scanner.nextInt();
            m2[i] = scanner.nextInt();
            m3[i] = scanner.nextInt();
            avg[i] = (m1[i] + m2[i] + m3[i]) / 3.0;
        }
    }

    static int findTopper(double[] avg) {
        double highest = 0;
        int index = 0;
        for (int i = 0; i < avg.length; i++) {
            if (avg[i] > highest) {
                highest = avg[i];
                index = i;
            }
        }
        return index;
    }

    static void sortByAverage(String[] names, int[] ids, int[] m1, int[] m2, int[] m3, double[] avg) {
        for (int i = 0; i < avg.length - 1;
