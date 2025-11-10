import java.util.*;

public class x{

    public static void main(String args[]) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter no. of students: ");
        int Total=s.nextInt();

        Students manager=new Students(Total,s);
        manager.Students_entry();

        System.out.println("Sort by Average? y/n");
        String c=s.next();
        if(c.equals("y")) manager.Sort();

        System.out.println("Search student by Student_Student_ID?");
        String d=s.next();
        if(d.equals("y")) manager.Search();

        System.out.println("Calculate grade?");
        String g=s.next();
        if(g.equals("y")) manager.Print_grades();

        System.out.println("Bye!");
    }
}

class Student {
    String name;
    int Student_ID;
    int Student_1;
    int Student_2;
    int Student_3;
    double average;

    Student(String name,int Student_ID,int Student_1,int Student_2,int Student_3) {
        this.name=name;this.Student_ID=Student_ID;this.Student_1=Student_1;this.Student_2=Student_2;this.Student_3=Student_3;
        Calculate_average();
    }

    void Calculate_average() {
        average=(Student_1+Student_2+Student_3)/3.0;
    }

    String grade() {
        if(average>=80) 
            return "A+";
        else if(average>=70) 
            return "A";
        else if(average>=60) 
            return "B";
        else if(average>=50) 
            return "C";
        else 
            return "F";
    }
}

class Students {
    Student[] students;
    int total;
    Scanner s;

    Students(int total,Scanner scanner) {
        this.total=total;
        this.s=scanner;
        students=new Student[total];
    }

    void Students_entry() {
        double top=0;
        int topIndex=0;
        for(int i=0;i<total;i++) {
            System.out.println("Enter name:");
            String name=s.next();
            System.out.println("Enter Student_Student_ID:");
            int Student_ID=s.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            int m1=s.nextInt();
            int m2=s.nextInt();
            int m3=s.nextInt();
            Student st=new Student(name,Student_ID,m1,m2,m3);
            students[i]=st;
            if(st.average>top) {
                top=st.average;
                topIndex=i;
            }
        }

        System.out.println("All Students:");
        for(int i=0;i<total;i++) {
            System.out.println("Name:"+students[i].name+" Student_Student_ID:"+students[i].Student_ID+" Average_marks:"+students[i].average);
        }

        System.out.println("Topper: "+students[topIndex].name+" Average_marks:"+students[topIndex].average);
    }

    void Sort() {
        for(int i=0;i<total-1;i++) {
            for(int j=i+1;j<total;j++) {
                if(students[i].average<students[j].average) {
                    Student temp=students[i];
                    students[i]=students[j];
                    students[j]=temp;
                }
            }
        }
        System.out.println("Sorted List:");
        for(int i=0;i<total;i++) {
            System.out.println(students[i].name+" "+students[i].average);
        }
    }

    void Search() {
        System.out.println("Enter Student_Student_ID:");
        int Temp_Student_ID=s.nextInt();
        boolean f=false;
        for(int i=0;i<total;i++) {
            if(students[i].Student_ID==Temp_Student_ID) {
                System.out.println("Found:"+students[i].name+" Average_marks:"+students[i].average);
                f=true;
            }
        }
    }

    void Print_grades() {
        for(int i=0;i<total;i++) {
            System.out.println(students[i].name+" Grade:"+students[i].grade());
        }
    }
}
