/**
 * Represents a single student record with ID, name, marks, and average.
 */
public class Student {
    private String name;
    private int id;
    private int mark1;
    private int mark2;
    private int mark3;
    private double average;

    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
    }

    /** Calculates and stores the average marks */
    public void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }

    public double getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | ID: " + id + " | Average: " + average;
    }
}
