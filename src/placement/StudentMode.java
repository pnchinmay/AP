package placement;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMode {
    public String name;
    public String RollNo;
    public float CGPA;
    public String Branch;

    Scanner sc=new Scanner(System.in);

    //	Empty Default constructor
    public StudentMode() {
    }

	public StudentMode(String name,String RollNo,float CGPA,String Branch) {
		this.name=name;
		this.RollNo=RollNo;
		this.CGPA=CGPA;
		this.Branch=Branch;
	}

    public void SetDetails(PlacementCellMode admin) {
        admin.StudentCount++;
        int index = admin.StudentCount-1;
        admin.Students.add(new Student());
        admin.Students.get(index).Id = admin.StudentCount;
        admin.Students.get(index).Name = this.name;
        admin.Students.get(index).RollNo = this.RollNo;
        admin.Students.get(index).CGPA = this.CGPA;
        admin.Students.get(index).Branch = this.Branch;
        admin.Students.get(index).Companies = new ArrayList<Company>(admin.Companies);
        admin.UnplacedCount++;

        GetDetailsArray(index+1, admin);
    }


    public void GetDetailsArray(int id, PlacementCellMode admin) {
        Student x = admin.Students.get(id-1);

        System.out.println("Here are the details for Id: "+ id);
        System.out.println(x.Id + "\n"
                + x.Name+ "\n"
                + x.RollNo + "\n"
                + x.CGPA + "\n"
                + x.Branch + "\n"
                + x.RegisterationTime + "\n"
                + x.CompanyAppliedCount + "\n"
                + x.CompanyOfferedCount + "\n"
                + x.CompanyAcceptedCount + "\n"
                + x.Status + "\n");

        System.out.println("No. of companies applied to: " + x.CompanyAppliedCount);
        System.out.println("No. of companies available: " + x.Companies.size());
        if(x.Companies.size()>0) {
            System.out.println("Details are: ");
            for(int i=0; i<x.Companies.size();i++) {
                Company s = x.Companies.get(i);
                System.out.println(x.Id + "\n"
                        + s.CompanyName + "\n"
                        + s.CompanyRole + "\n"
                        + s.Package + "\n"
                        + s.CGPA + "\n"
                        + s.application + "\n"
                        + s.StudentsOfferedCount + "\n");
            }
        }
    }



}
