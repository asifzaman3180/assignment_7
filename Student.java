package mypackage;

public class Student {
    String name;
    int id;
    int mark1, mark2, mark3;
    double average;

    Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    void calculateAverage() {
        average = (mark1 + mark2 + mark3) / 3.0;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", Avg: " + average + ")";
    }
}
