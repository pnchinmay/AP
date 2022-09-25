package placement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Student {
    public int Id;
    public String Name;
    public String RollNo;
    public float CGPA = 0;
    public String Branch ;
    public Date RegisterationTime;

    public int Registered = 0;
    public int CompanyAppliedCount = 0;
    public int CompanyOfferedCount = 0;
    public int CompanyRejectedCount = 0;
    public int CompanyAcceptedCount = 0;
    public float maxPackageOffered = 0;
//	public int StudentsOfferedCount=0;

//	For individual companies
//	applied, not applied
//	public String application = "not applied";

    //	For placement cell
//	Placed, Unplaced, Blocked in case of 0 acceptance
    public String Status = "Unplaced";

    //	offered, not offered
    public int offered = 0;

    //	enter details based on the data from Companies Objects
    public ArrayList<Company> Companies= new ArrayList<Company>(5);

    //	Companies with offer
    public ArrayList<Company> AppliedCompany = new ArrayList<Company>(1);

    //	Companies with offer
    public ArrayList<Company> OfferedCompany = new ArrayList<Company>(1);

    //	Offer accepted company
    public ArrayList<Company> AcceptedCompany= new ArrayList<Company>(1);

//    Highest CTC Offer
    public int HighestCTCCompanyID = -1;

    //	Scanner class object
    Scanner sc=new Scanner(System.in);


    public void SetRegistrationTime(PlacementCellMode admin, int Id) {
        if(admin.RegistrationDates[2]==null)
            System.out.println("Registration has not yet begun");
        else {
            Date date1;
            System.out.print("Enter in the format \"18-07-2022-18-45-PM\": ");
            String s = sc.next();
            try {
                date1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").parse(s);
                System.out.print("You entered: ");
                System.out.println(new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").format(date1));

                if (date1.before(admin.RegistrationDates[3]) && date1.after(admin.RegistrationDates[2]) || date1.equals(admin.RegistrationDates[2]) || date1.equals(admin.RegistrationDates[3])) {
                    this.Registered = 1;
                    this.RegisterationTime = date1;
                    System.out.println(date1 + "\n"
                            + "Registered!!");
                    this.GetDetails();
                    admin.Students.get(Id - 1).RegisterationTime = date1;
                    System.out.println(this.Name + "Registered for the Placement Drive at IIITD!!!\n"
                            + "Your details are: \n"
                            + "Name: " + this.Name + "\n"
                            + "RollNo: " + this.RollNo + "\n"
                            + "CGPA: " + this.CGPA + "\n"
                            + "Branch: " + this.Branch + "\n");
                }
                else
                    System.out.println("You aren't registered. Check dates again");
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
        System.out.println(this.Id + newline+
                this.Name +newline+
                this.RollNo +newline+
                this.CGPA+newline+
                this.Branch+newline+
                this.RegisterationTime + newline +
                this.CompanyAppliedCount + newline);
//				this.application + newline +
//				this.Status);
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
        System.out.println("ArrayDetails done");
    }

//    Set maxPackageOffered and it's company details
    public void SetMaxPackageOffered(int id, PlacementCellMode admin) {
        Student x = admin.Students.get(id-1);
        for(int i=0; i<x.OfferedCompany.size(); i++) {
            if(x.OfferedCompany.get(i).Package > x.maxPackageOffered)
                x.maxPackageOffered = x.OfferedCompany.get(i).Package;
                x.HighestCTCCompanyID = i+1;
        }
    }

    public int GetAppliedCompanyIndex(PlacementCellMode admin, int id) {
        for (int i = 0; i < this.AppliedCompany.size(); i++) {
            Company object = this.AppliedCompany.get(i);
            if (object !=null && object.Id == id) {
                return i;
            }
        }
        return -1;
    }
}
