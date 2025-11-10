import java.util.*;

public class StudentApp {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of students: ");
        int totalStudents = scanner.nextInt();

        String studentNames[] = new String[totalStudents];
        int studentIds[] = new int[totalStudents];
        int subject1Marks[] = new int[totalStudents];
        int subject2Marks[] = new int[totalStudents];
        int subject3Marks[] = new int[totalStudents];
        double studentAverages[] = new double[totalStudents];

        double highestAverage = 0;
        int topperIndex = 0;

        for (int i = 0; i < totalStudents; i++) {
            System.out.println("Enter name:");
            studentNames[i] = scanner.next();
            System.out.println("Enter ID:");
            studentIds[i] = scanner.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            subject1Marks[i] = scanner.nextInt();
            subject2Marks[i] = scanner.nextInt();
            subject3Marks[i] = scanner.nextInt();

            studentAverages[i] = (subject1Marks[i] + subject2Marks[i] + subject3Marks[i]) / 3.0;

            if (studentAverages[i] > highestAverage) {
                highestAverage = studentAverages[i];
                topperIndex = i;
            }
        }

        System.out.println("All Students:");
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("Name: " + studentNames[i] + " ID: " + studentIds[i] + " Average: " + studentAverages[i]);
        }

        System.out.println("Topper: " + studentNames[topperIndex] + " Average: " + studentAverages[topperIndex]);

        System.out.println("Sort by Average? (y/n)");
        String sortChoice = scanner.next();

        if (sortChoice.equals("y")) {
            for (int i = 0; i < totalStudents - 1; i++) {
                for (int j = i + 1; j < totalStudents; j++) {
                    if (studentAverages[i] < studentAverages[j]) {
                        String tempName = studentNames[i];
                        studentNames[i] = studentNames[j];
                        studentNames[j] = tempName;

                        int tempId = studentIds[i];
                        studentIds[i] = studentIds[j];
                        studentIds[j] = tempId;

                        int tempSub1 = subject1Marks[i];
                        subject1Marks[i] = subject1Marks[j];
                        subject1Marks[j] = tempSub1;

                        int tempSub2 = subject2Marks[i];
                        subject2Marks[i] = subject2Marks[j];
                        subject2Marks[j] = tempSub2;

                        int tempSub3 = subject3Marks[i];
                        subject3Marks[i] = subject3Marks[j];
                        subject3Marks[j] = tempSub3;

                        double tempAverage = studentAverages[i];
                        studentAverages[i] = studentAverages[j];
                        studentAverages[j] = tempAverage;
                    }
                }
            }

            System.out.println("Sorted List:");
            for (int i = 0; i < totalStudents; i++) {
                System.out.println(studentNames[i] + " " + studentAverages[i]);
            }
        }

        System.out.println("Search student by ID? (y/n)");
        String searchChoice = scanner.next();

        if (searchChoice.equals("y")) {
            System.out.println("Enter ID:");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < totalStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.println("Found: " + studentNames[i] + " Average: " + studentAverages[i]);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Not found");
            }
        }

        System.out.println("Calculate grade? (y/n)");
        String gradeChoice = scanner.next();

        if (gradeChoice.equals("y")) {
            for (int i = 0; i < totalStudents; i++) {
                String grade = "";
                if (studentAverages[i] >= 80)
                    grade = "A+";
                else if (studentAverages[i] >= 70)
                    grade = "A";
                else if (studentAverages[i] >= 60)
                    grade = "B";
                else if (studentAverages[i] >= 50)
                    grade = "C";
                else
                    grade = "F";

                System.out.println(studentNames[i] + " Grade: " + grade);
            }
        }

        System.out.println("Bye!");
    }
}
