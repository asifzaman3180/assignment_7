import java.util.*;  // Importing the Scanner class for taking user input

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object for input

        System.out.print("Enter number of students: ");
        int numberOfStudents = scanner.nextInt(); // মোট কয়জন ছাত্র তার সংখ্যা নেওয়া হচ্ছে

        // Arrays for storing student data
        String[] studentNames = new String[numberOfStudents];   // নাম রাখার জন্য
        int[] studentIds = new int[numberOfStudents];           // আইডি রাখার জন্য
        int[] subject1Marks = new int[numberOfStudents];        // বিষয় ১ এর নাম্বার
        int[] subject2Marks = new int[numberOfStudents];        // বিষয় ২ এর নাম্বার
        int[] subject3Marks = new int[numberOfStudents];        // বিষয় ৩ এর নাম্বার
        double[] averageMarks = new double[numberOfStudents];   // গড় নাম্বার রাখার জন্য

        double highestAverage = 0;  // এখন পর্যন্ত সর্বোচ্চ গড়
        int topperIndex = 0;        // টপারের index রাখার জন্য

        // --------- ছাত্রদের ডাটা ইনপুট নেওয়া হচ্ছে ----------
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");

            System.out.print("Enter name: ");
            studentNames[i] = scanner.next(); // নাম ইনপুট

            System.out.print("Enter ID: ");
            studentIds[i] = scanner.nextInt(); // আইডি ইনপুট

            System.out.print("Enter marks of 3 subjects: ");
            subject1Marks[i] = scanner.nextInt(); // বিষয় ১ এর নাম্বার
            subject2Marks[i] = scanner.nextInt(); // বিষয় ২ এর নাম্বার
            subject3Marks[i] = scanner.nextInt(); // বিষয় ৩ এর নাম্বার

            // গড় নাম্বার হিসাব
            averageMarks[i] = (subject1Marks[i] + subject2Marks[i] + subject3Marks[i]) / 3.0;

            // টপার চেক করা হচ্ছে
            if (averageMarks[i] > highestAverage) {
                highestAverage = averageMarks[i];
                topperIndex = i;
            }
        }

        // --------- সব ছাত্রদের তথ্য দেখানো ----------
        System.out.println("\nAll Students:");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Name: " + studentNames[i] +
                               " | ID: " + studentIds[i] +
                               " | Average: " + averageMarks[i]);
        }

        // --------- টপার দেখানো ----------
        System.out.println("\nTopper: " + studentNames[topperIndex] +
                           " | Average: " + averageMarks[topperIndex]);

        // --------- Sort করা হবে কিনা ----------
        System.out.print("\nSort by average? (y/n): ");
        String sortChoice = scanner.next(); // ইউজারের পছন্দ নেওয়া হচ্ছে

        if (sortChoice.equalsIgnoreCase("y")) {
            // Bubble Sort (Descending Order)
            for (int i = 0; i < numberOfStudents - 1; i++) {
                for (int j = i + 1; j < numberOfStudents; j++) {
                    if (averageMarks[i] < averageMarks[j]) {
                        // সব ডাটার জায়গা অদল-বদল করা হচ্ছে
                        String tempName = studentNames[i];
                        studentNames[i] = studentNames[j];
                        studentNames[j] = tempName;

                        int tempId = studentIds[i];
                        studentIds[i] = studentIds[j];
                        studentIds[j] = tempId;

                        int tempM1 = subject1Marks[i];
                        subject1Marks[i] = subject1Marks[j];
                        subject1Marks[j] = tempM1;
