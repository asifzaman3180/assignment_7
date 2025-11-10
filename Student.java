public class Student {
    private String name;
    private int id;
    private int m1, m2, m3;
    private double average;

    public Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        calculateAverage();
    }

    public void calculateAverage() {
        this.average = (m1 + m2 + m3) / 3.0;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public double getAverage() { return average; }

    @Override
    public String toString() {
        return String.format("Name: %s | ID: %d | Avg: %.2f", name, id, average);
    }
}
