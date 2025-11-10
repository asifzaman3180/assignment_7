import java.util.*;

public class x{

    static void Results(int Total) {
        Scanner s = new Scanner(System.in);
        String name[]=new String[Total];
        int Student_ID[]=new int[Total];
        int Subject1_mark[]=new int[Total];
        int Subject2_mark[]=new int[Total];
        int Subject3_mark[]=new int[Total];
        double Average_marks[]=new double[Total];
        double top=0;
        int topIndex=0;

        for(int i=0;i<Total;i++) {
            System.out.println("Enter name:");
            name[i]=s.next();
            System.out.println("Enter Student_ID:");
            Student_ID[i]=s.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            Subject1_mark[i]=s.nextInt();
            Subject2_mark[i]=s.nextInt();
            Subject3_mark[i]=s.nextInt();
            Average_marks[i]=(Subject1_mark[i]+Subject2_mark[i]+Subject3_mark[i])/3.0;
            if(Average_marks[i]>top) {
                top=Average_marks[i];
                topIndex=i;
            }
        }

        System.out.println("All Students:");

        for(int i=0;i<Total;i++) {
            System.out.println("Name:"+name[i]+" Student_ID:"+Student_ID[i]+" Average_marks:"+Average_marks[i]);
        }

        System.out.println("Topper: "+name[topIndex]+" Average_marks:"+Average_marks[topIndex]);
        
    }

    static void Sort_Students(String name[], double Average_marks[], int Subject1_mark[], int Subject2_mark[], int Subject3_mark[], int Student_ID[], int Total, String c) {
        if(c.equals("y")) {
            for(int i=0;i<Total-1;i++) {
                for(int j=i+1;j<Total;j++) {
                    if(Average_marks[i]<Average_marks[j]) {
                    String Temp_Name=name[i];
                    name[i]=name[j];
                    name[j]=Temp_Name;
                    int Temp_ID=Student_ID[i];
                    Student_ID[i]=Student_ID[j];
                    Student_ID[j]=Temp_ID;
                    int Temp1_mark=Subject1_mark[i];
                    Subject1_mark[i]=Subject1_mark[j];
                    Subject1_mark[j]=Temp1_mark;
                    int Temp2_mark=Subject2_mark[i];
                    Subject2_mark[i]=Subject2_mark[j];
                    Subject2_mark[j]=Temp2_mark;
                    int Temp3_mark=Subject3_mark[i];
                    Subject3_mark[i]=Subject3_mark[j];
                    Subject3_mark[j]=Temp3_mark;
                    double Temp_average=Average_marks[i];
                    Average_marks[i]=Average_marks[j];
                    Average_marks[j]=Temp_average;
                    }
                }
            }
            System.out.println("Sorted List:");
            for(int i=0;i<Total;i++) {
                System.out.println(name[i]+" "+Average_marks[i]);
            }
        }
    }

    static void Search_ID(String name[], double Average_marks[], int Student_ID[], String d, int Total) {

        if(d.equals("y")) {
            Scanner s = new Scanner(System.in);

            System.out.println("Enter Student_ID:");
            int Temp_ID=s.nextInt();
            boolean f=false;
            for(int i=0;i<Total;i++) {
                if(Student_ID[i]==Temp_ID) {
                System.out.println("Found:"+name[i]+" Average_marks:"+Average_marks[i]);
                f=true;
            }
            }
            if(!f) System.out.println("Not found");
        }
    }

    static void Calculate_grade(double Average_marks[], String name[], String g, int Total) {
        if(g.equals("y")) {
            for(int i=0;i<Total;i++) {
                String grade="";
                if(Average_marks[i]>=80) 
                    grade="A+";
                else if(Average_marks[i]>=70) 
                    grade="A";
                else if(Average_marks[i]>=60) 
                    grade="B";
                else if(Average_marks[i]>=50) 
                    grade="C";
                else 
                    grade="F";
                System.out.println(name[i]+" Grade:"+grade);
            }
        }
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter no. of students: ");
        int Total=s.nextInt();
        String name[]=new String[Total];
        int Student_ID[]=new int[Total];
        int Subject1_mark[]=new int[Total];
        int Subject2_mark[]=new int[Total];
        int Subject3_mark[]=new int[Total];
        double Average_marks[]=new double[Total];

        Results(Total);

        System.out.println("Sort by Average? y/n");
        String c=s.next();

        Sort_Students(name, Average_marks, Subject1_mark, Subject2_mark, Subject3_mark, Student_ID, Total, c);
        
        System.out.println("Search student by Student_ID?");
        String d=s.next();
        
        Search_ID(name, Average_marks, Student_ID, d, Total);

        System.out.println("Calculate grade?");
        String g=s.next();

        Calculate_grade(Average_marks, name, g, Total);
        
        System.out.println("Bye!");
    }
}
