import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();

        String[] names = new String[count];
        int[] ids = new int[count];
        int[] m1 = new int[count];
        int[] m2 = new int[count];
        int[] m3 = new int[count];
        double[] avg = new double[count];

        inputStudents(scanner, names, ids, m1, m2, m3, avg);
        printAllStudents(names, ids, avg);
        printTopper(names, avg);
        sortIfRequested(scanner, names, ids, m1, m2, m3, avg);
        searchIfRequested(scanner, names, ids, avg);
        gradeIfRequested(scanner, names, avg);
    }

    private static void inputStudents(Scanner sc, String[] names, int[] ids,
                                      int[] m1, int[] m2, int[] m3, double[] avg) {
        double top = 0;
        for (int i = 0; i < names.length; i++) {
            System.out.print("Enter name: ");
            names[i] = sc.next();
            System.out.print("Enter id: ");
            ids[i] = sc.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            m1[i] = sc.nextInt();
            m2[i] = sc.nextInt();
            m3[i] = sc.nextInt();
            avg[i] = (m1[i] + m2[i] + m3[i]) / 3.0;
        }
    }

    private static void printAllStudents(String[] names, int[] ids, double[] avg) {
        System.out.println("All Students:");
        for (int i = 0; i < names.length; i++) {
            System.out.println("Name: " + names[i] + " Id: " + ids[i] + " Avg: " + avg[i]);
        }
    }

    private static void printTopper(String[] names, double[] avg) {
        double top = avg[0];
        int index = 0;
        for (int i = 1; i < avg.length; i++) {
            if (avg[i] > top) {
                top = avg[i];
                index = i;
            }
        }
        System.out.println("Topper: " + names[index] + " Avg: " + avg[index]);
    }

    private static void sortIfRequested(Scanner sc, String[] names, int[] ids,
                                        int[] m1, int[] m2, int[] m3, double[] avg) {
        System.out.print("Sort by Average? (y/n): ");
        if (!sc.next().equalsIgnoreCase("y")) return;

        for (int i = 0; i < names.length - 1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (avg[i] < avg[j]) {
                    swap(names, i, j);
                    swap(ids, i, j);
                    swap(m1, i, j);
                    swap(m2, i, j);
                    swap(m3, i, j);
                    swap(avg, i, j);
                }
            }
        }
        System.out.println("Sorted List:");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " " + avg[i]);
        }
    }

    private static void searchIfRequested(Scanner sc, String[] names, int[] ids, double[] avg) {
        System.out.print("Search student by id? (y/n): ");
        if (!sc.next().equalsIgnoreCase("y")) return;

        System.out.print("Enter id: ");
        int sid = sc.nextInt();
        boolean found = false;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == sid) {
                System.out.println("Found: " + names[i] + " Avg: " + avg[i]);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Not found");
    }

    private static void gradeIfRequested(Scanner sc, String[] names, double[] avg) {
        System.out.print("Calculate grade? (y/n): ");
        if (!sc.next().equalsIgnoreCase("y")) return;

        for (int i = 0; i < names.length; i++) {
            String grade;
            if (avg[i] >= 80) grade = "A+";
            else if (avg[i] >= 70) grade = "A";
            else if (avg[i] >= 60) grade = "B";
            else if (avg[i] >= 50) grade = "C";
            else grade = "F";

            System.out.println(names[i] + " Grade: " + grade);
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
}
