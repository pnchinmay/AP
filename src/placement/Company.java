package placement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Company{
    public String CompanyName;
    public String CompanyRole ;
    public float Package;
    public float CGPA ;
    public int Id;
    public int Registered = 0;
    public Date RegisterationTime;
    public int StudentsOfferedCount=0;
    public int StudentsRegisteredCount=0;
    public ArrayList<Student> Students= new ArrayList<Student>(5);
    public ArrayList<Student> StudentsOffered= new ArrayList<Student>(5);

    //		For individual student
    public String application = "not applied";
    public String eligibility = "Unavailable";
    public int offered = 0;

    //		Scanner class object
    Scanner sc=new Scanner(System.in);

    //		Default empty constructor
    public Company(){
    }


    public Company(String CompanyName,String CompanyRole,float Package,float CGPA, int id,Date time) {
        this.CompanyName=CompanyName;
        this.CompanyRole=CompanyRole;
        this.Package=Package;
        this.CGPA=CGPA;
        this.Id = id;
        this.RegisterationTime=time;
    }


    public void SetRole(PlacementCellMode admin) {
        this.CompanyRole=sc.nextLine();
        // admin.Companies.set(Id-1,CompanyRole);
        admin.Companies.get(Id-1).CompanyRole = this.CompanyRole;
        GetDetailsArray(Id, admin);
    }


    public void SetPackage(PlacementCellMode admin) {
        this.Package=sc.nextFloat();
//			admin.Companies.set(Id-1,Package);
        admin.Companies.get(Id-1).Package = this.Package;
        GetDetailsArray(Id, admin);
    }


    public void SetCGPA(PlacementCellMode admin) {
        this.CGPA=sc.nextFloat();
        admin.Companies.get(Id-1).CGPA = this.CGPA;
        GetDetailsArray(Id, admin);
    }


    public void SetRegistrationTime(PlacementCellMode admin) {
        if(admin.RegistrationDates[0]==null)
            System.out.println("Registration has not yet begun");
        else {
            Date date1;
            System.out.print("Enter in the format \"18-07-2022-18-45-PM\": ");
            String s = sc.next();
            try {
                date1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").parse(s);
                System.out.print("You entered: ");
                System.out.println(new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").format(date1));
                admin.RegistrationDates[0] = date1;
                if (date1.before(admin.RegistrationDates[1]) && date1.after(admin.RegistrationDates[0]) || date1.equals(admin.RegistrationDates[0]) || date1.equals(admin.RegistrationDates[1])) {
                    this.Registered = 1;
                    this.RegisterationTime = date1;
                    System.out.println(date1 + "\n"
                            + "Registered!!");
                    this.GetDetails();
                    admin.Companies.get(Id - 1).RegisterationTime = date1;
                }
                else{
                    System.out.println("Not registered");
                }
            } catch (ParseException e) {
                System.out.println("Invalid Date Format\n"
                        + "Try again in the format 18-07-2022-18-45-PM");
            }

            System.out.println("ID is: " + Id);
            GetDetailsArray(Id, admin);
        }
    }


    public void GetDetails() {
        String newline = System.getProperty("line.separator");
        System.out.println(this.CompanyName +newline+
                this.CompanyRole +newline+
                this.Package+newline+
                this.CGPA+newline+
                this.Id+newline+
                this.RegisterationTime);
    }


    public void GetDetailsArray(int id, PlacementCellMode admin) {
        Company x = new Company();
        x = admin.Companies.get(id-1);
        String name = x.CompanyName;
        String role = x.CompanyRole;
        float ctc = x.Package;
        float gpa = x.CGPA;
        Date time = x.RegisterationTime;
        System.out.println("Here are the details for Id: "+ id);
        System.out.println(name+ " " + role + " " + ctc + " " + gpa + " " + id + " " + time + "\n");
    }
}