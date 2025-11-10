import java.util.*;
public class x{
public static void main(String args[]){
Scanner s=new Scanner(System.in);
System.out.println("Enter no. of students: ");
int n=s.nextInt();
String nm[]=new String[n];
int id[]=new int[n];
int m1[]=new int[n];
int m2[]=new int[n];
int m3[]=new int[n];
double avg[]=new double[n];
double top=0;
int topIndex=0;
for(int i=0;i<n;i++){
System.out.println("Enter name:");
nm[i]=s.next();
System.out.println("Enter id:");
id[i]=s.nextInt();
System.out.println("Enter marks of 3 subjects:");
m1[i]=s.nextInt();
m2[i]=s.nextInt();
m3[i]=s.nextInt();
avg[i]=(m1[i]+m2[i]+m3[i])/3.0;
if(avg[i]>top){
top=avg[i];
topIndex=i;
}
}
System.out.println("All Students:");
for(int i=0;i<n;i++){
System.out.println("Name:"+nm[i]+" Id:"+id[i]+" Avg:"+avg[i]);
}
System.out.println("Topper: "+nm[topIndex]+" Avg:"+avg[topIndex]);
System.out.println("Sort by Average? y/n");
String c=s.next();
if(c.equals("y")){
for(int i=0;i<n-1;i++){
for(int j=i+1;j<n;j++){
if(avg[i]<avg[j]){
String tnm=nm[i];
nm[i]=nm[j];
nm[j]=tnm;
int tid=id[i];
id[i]=id[j];
id[j]=tid;
int tm1=m1[i];
m1[i]=m1[j];
m1[j]=tm1;
int tm2=m2[i];
m2[i]=m2[j];
m2[j]=tm2;
int tm3=m3[i];
m3[i]=m3[j];
m3[j]=tm3;
double tav=avg[i];
avg[i]=avg[j];


avg[j]=tav;
}
}
}
System.out.println("Sorted List:");
for(int i=0;i<n;i++){
System.out.println(nm[i]+" "+avg[i]);
}
}
System.out.println("Search student by id?");
String d=s.next();
if(d.equals("y")){
System.out.println("Enter id:");
int sid=s.nextInt();
boolean f=false;
for(int i=0;i<n;i++){
if(id[i]==sid){
System.out.println("Found:"+nm[i]+" Avg:"+avg[i]);
f=true;
}
}
if(!f) System.out.println("Not found");
}
System.out.println("Calculate grade?");
String g=s.next();
if(g.equals("y")){
for(int i=0;i<n;i++){
String grade="";
if(avg[i]>=80) grade="A+";
else if(avg[i]>=70) grade="A";
else if(avg[i]>=60) grade="B";
else if(avg[i]>=50) grade="C";
else grade="F";
System.out.println(nm[i]+" Grade:"+grade);
}
}
System.out.println("Bye!");
}
}
