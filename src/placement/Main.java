package placement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    //	Options available to students
    public static void StudentOptions(PlacementCellMode admin){

//		Scanner class object
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("1) Enter as a Student(Give Student Name, and Roll No.) \n"
                    + "2) Add students \n"
                    + "3) Back \n");

            int choice=sc.nextInt();
			sc.nextLine();


//			1) Enter as a student (Give Student Name and Roll No.)
            if(choice==1) {
                System.out.println("Enter name: \n");
                String name= sc.nextLine();
                System.out.println("Enter rollNo: ");
                String RollNo = sc.next();
                int Id = GetStudentId(admin, RollNo);
                if(Id>0) {
                    Student s = admin.Students.get(Id-1);
                    SingleStudentOptions(admin, Id);
                }
                else {
                    StudentMode s = new StudentMode(name, RollNo);
                    s.SetDetails(admin);
                }
            }

//			2) Add students
            else if(choice==2) {
                System.out.print("Number of students to add: ");
                int studentCount = sc.nextInt();
                sc.nextLine();
                for(int i=0; i<studentCount; i++) {
                    System.out.println("Enter details: ");
                    String name= sc.nextLine();
                    String RollNo = sc.nextLine();
                    Float cgpa = sc.nextFloat();
                    sc.nextLine();
                    String branch = sc.nextLine();
                    StudentMode s = new StudentMode(name, RollNo, cgpa, branch);
                    s.SetDetails(admin);
                }
            }

//			3) Back
            else if(choice==3)
                break;

            else
                break;
        }
    }

    //	Options available to Placement Cell
    public static void PlacementCellOptions(PlacementCellMode admin) {

//		Scanner class object
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("Welcome to IIITD Placement Cell \n"
                    + "1) Open Student Registrations \n"
                    + "2) Open Company Registrations \n"
                    + "3) Get Number of Student Registrations \n"
                    + "4) Get Number of Company Registrations \n"
                    + "5) Get Number of Offered/Unoffered/Blocked Students \n"
                    + "6) Get Student Details \n"
                    + "7) Get Company Details \n"
                    + "8) Get Average Package \n"
                    + "9) Get Company Process Results \n"
                    + "10) Back");

            int choice=sc.nextInt();

//			1) Open Student Registrations
            if(choice==1) {
                System.out.println("1) Set the Opening Time for Student Registrations");
                System.out.print("Enter in the format \"18-07-2022-18-45-PM\": ");
                Date date1;
//				Input date
                String s = sc.next();
                System.out.println("You entered: " + s);
                try {
                    date1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").parse(s);
                    System.out.print("You entered: ");
                    System.out.println(new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").format(date1));
                    admin.RegistrationDates[2] = date1;
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format\n"
                            + "Try again in the format 18-07-2022-18-45-PM");
                }
                System.out.println("2) Set the Ending Time for Student Registrations");
                s = sc.next();
                try {
                    date1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").parse(s);
                    System.out.print("You entered: ");
                    System.out.println(new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").format(date1));
                    admin.RegistrationDates[3] = date1;
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format\n"
                            + "Try again in the format 18-07-2022-18-45-PM");
                }
            }

//			2) Open Company Registrations
            else if(choice==2) {
                System.out.println("1) Set the Opening Time for Company Registrations");
                System.out.print("Enter in the format \"18-07-2022-18-45-PM\": ");
                Date date1;
//				Input date
                String s = sc.next();
                System.out.println("You entered: " + s);
                try {
                    date1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").parse(s);
                    System.out.print("You entered: ");
                    System.out.println(new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").format(date1));
                    admin.RegistrationDates[0] = date1;
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format\n"
                            + "Try again in the format 18-07-2022-18-45-PM");
                }
                System.out.println("2) Set the Ending Time for Company Registrations");
                s = sc.next();
                try {
                    date1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").parse(s);
                    System.out.print("You entered: ");
                    System.out.println(new SimpleDateFormat("dd-MM-yyyy-HH-mm-a").format(date1));
                    admin.RegistrationDates[1] = date1;
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format\n"
                            + "Try again in the format 18-07-2022-18-45-PM");
                }
            }

//			3) Get Number of Student Registrations
            else if(choice==3) {
                System.out.println(admin.StudentCount);
            }

//			4) Get Number of Company Registrations
            else if(choice==4) {
                System.out.println(admin.CompanyCount);
            }

//			5) Get Number of Placed/Unplaced/Blocked Students
            else if(choice==5) {
                System.out.println("Placed: " + admin.PlacedCount);
                System.out.println("Unplaced: " + admin.UnplacedCount);
                System.out.println("Blocked: " + admin.BlockedCount);
            }

//			6) Get Student Details
            else if(choice==6) {
                System.out.println("Enter student's name and rollNo: ");
                String name = sc.next();
                String rollNo = sc.next();
                int i = GetStudentId(admin, rollNo);
                i--;
                Student s = admin.Students.get(i);
                System.out.println("Student Details are:\n"
                        + "Student Name: " + s.Name + "\n"
                        + "Student RollNo: " + s.RollNo + "\n"
                        + "Student CGPA: " + s.CGPA + "\n"
                        + "Student Branch: " + s.Branch + "\n"
                        + "Companies Applied for: " + s.AppliedCompany.size());
                for(i=0; i<s.AppliedCompany.size(); i++) {
                    Company c = s.AppliedCompany.get(i);
                    System.out.println("\n" + c.Id +") CompanyName: "+ c.CompanyName
                            + "\n  Company role offering: " + c.CompanyRole
                            + "\n  Company Package: " + c.Package + " LPA"
                            + "\n  Company CGPA Criteria: " + c.CGPA + "\n");
                    if(s.AppliedCompany.get(i).offered == 1)
                        System.out.println("\n Offered: yes");
                    else
                        System.out.println("\n Offered: no");
                }

            }

//			7) Get Company Details
            else if(choice==7) {
                if(admin.CompanyCount!=0) {
                    System.out.print("Choose Company: ");
                    for(int i=0;i<admin.CompanyCount;i++) {
                        int compID = admin.Companies.get(i).Id;
                        String compName = admin.Companies.get(i).CompanyName;
                        System.out.println(compID +") "+ compName);
                    }
                    System.out.println("Enter compnay name: ");
                    String name= sc.nextLine();
                    int id = GetCompanyId(admin, name);
                    if(id>0)
                        GetCompanyDetails(admin,id);
                    else
                        System.out.println("No such company present");
                }
            }

//			8) Get Average Package
            else if(choice==8) {
                float sum = 0;
                for(int i =0; i<admin.Packages.size(); i++) {
                    sum += admin.Packages.get(i);
                }
                float AvgPackage = sum/admin.Packages.size();
                System.out.println(AvgPackage);
            }

//			9) Get Company Process Results
            else if(choice==9) {
                String company = sc.next();
                int compId = GetCompanyId(admin, company);
                Company c = admin.Companies.get(compId-1);
                for(int i=0; i<c.StudentsOffered.size(); i++) {
                    Student selected = c.StudentsOffered.get(i);
                    int j = i+1;
                    System.out.println(j + ") Student Name: " + selected.Name + "\n"
                            + "Student RollNo: " + selected.RollNo + "\n"
                            + "Student CGPA: " + selected.CGPA + "\n"
                            + "Student Branch: " + selected.Branch + "\n");
                }
            }

//			10) Back
            else if(choice==10)
                break;

            else
                break;
        }
    }

    //	Options available to all companies
    public static void CompanyOptions(PlacementCellMode admin){

//		Scanner class object
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("Choose the Company Query to perform-"
                    + "1) Add Company and Details \n"
                    + "2) Choose Company \n"
                    + "3) Get Available Companies \n"
                    + "4) Back");

            int choice1=sc.nextInt();

//				1) Add Company and Details
            if(choice1==1) {
                if(admin.StudentCount<1) {
                    CompanyMode s=new CompanyMode();
                    s.SetDetails(admin);
                }
                else {
                    System.out.println("Company registration not allowed after student registration has begun!!");
                }
            }

//				2) Choose Company
            else if(choice1==2) {
                if(admin.CompanyCount!=0) {
                    System.out.print("Choose to enter into mode of Available Companies: ");
                    for(int i=0;i<admin.CompanyCount;i++) {
                        int compID = admin.Companies.get(i).Id;
                        String compName = admin.Companies.get(i).CompanyName;
                        System.out.println(compID +") "+ compName);
                    }
                    int id = sc.nextInt();
                    SingleCompanyOptions(admin, id);
                }
                else
                    System.out.println("No companies to choose from :(");
            }

//				3) Get Available Companies
            else if(choice1==3) {
                if(admin.CompanyCount!=0) {
                    for(int i=0;i<admin.CompanyCount;i++) {
                        System.out.println(admin.Companies.get(i).Id +") "+ admin.Companies.get(i).CompanyName);
                    }
                }
                else
                    System.out.println("No companies to show");

            }

//				4) Back
            else if(choice1==4) {
                break;
            }
        }
    }

    //	Options available to a single company
    public static void SingleCompanyOptions(PlacementCellMode admin, int id){
//		Scanner class object
        Scanner sc=new Scanner(System.in);

        Company s=admin.Companies.get(id-1);
        while(true) {
            System.out.println("Welcome "+s.CompanyName);

            System.out.println("1) Update Role \n"
                    + "2) Update Package \n"
                    + "3) Update CGPA criteria \n"
                    + "4) Register To Institute Drive \n"
                    + "5) Back \n");

            int choice2=sc.nextInt();

            if(choice2==1) {
                s.SetRole(admin);
            }

            else if(choice2==2) {
                s.SetPackage(admin);
            }

            else if(choice2==3) {
                s.SetCGPA(admin);
            }

            else if(choice2==4) {
                s.SetRegistrationTime(admin);
            }

            else if(choice2==5) {
                break;
            }
            else
                break;
        }
    }

    //	Options available to a single student
    public static void SingleStudentOptions(PlacementCellMode admin, int id){
//		Scanner class object
        Scanner sc=new Scanner(System.in);

        while(true) {
            Student s = admin.Students.get(id-1);
            System.out.println("Welcome " + s.Name + "!!");
            s.GetDetailsArray(id, admin);
            System.out.println("\n\t 1) Register for Placement Drive"
                    + "\n\t 2) Register for Company"
                    + "\n\t 3) Get All Available companies"
                    + "\n\t 4) Get Current Status"
                    + "\n\t 5) Update CGPA"
                    + "\n\t 6) Accept Offer"
                    + "\n\t 7) Reject Offer"
                    + "\n\t 8) Back\n");
            System.out.println("Enter choice: ");

            int choice2=sc.nextInt();

//			1) Register for Placement Drive
            if(choice2==1) {
                s.SetRegistrationTime(admin,id);
            }

//			2) Register for Company
            else if(choice2==2) {
                if(s.Companies.size()>0) {
                    System.out.print("Available Companies: ");
                    for (int i = 0; i < s.Companies.size(); i++) {
                        Company c = s.Companies.get(i);
                        int compID = c.Id;
                        String compName = c.CompanyName;
                        String compEligibility = c.eligibility;
                        if (s.CGPA < c.CGPA)
                            c.eligibility = "Unavailable";
                        if(c.Registered==1)
                            System.out.println(compID + ") " + compName + " - " + compEligibility);
                    }
                    System.out.print("Enter choice: ");

                    //	Company ID selected
                    int ch = sc.nextInt();

                    //				Company inside companies arraylist of student object
                    Company c = s.Companies.get(ch - 1);
                    if(c.Registered==1) {
                        String compName = c.CompanyName;
                        String compRole = c.CompanyRole;
                        String compEligibility = c.eligibility;
                        int flag = 0;
                        if (s.CompanyOfferedCount > 0 && c.Package >= 3 * s.maxPackageOffered)
                            flag = 1;
                        else if (s.CompanyOfferedCount == 0)
                            flag = 1;
                        if (compEligibility == "Available" && s.Status == "Unplaced" && flag == 1) {
                            System.out.println("Successfully Registered for " + compRole + " Role at " + compName);

                            //					Updating in companies arraylist of Student object
                            s.CompanyAppliedCount++;
                            s.AppliedCompany.add(c);
                            c.application = "applied";

                            //					Updating student in companies arraylist in admin object
                            //					Company inside companies arraylist of student object
                            c = admin.Companies.get(ch - 1);
                            c.Students.add(s);
                            c.StudentsRegisteredCount++;

                            //					Company selecting/offering the candidate
                            if (c.CGPA <= s.CGPA) {
                                c.StudentsOfferedCount++;
                                admin.OffersCount++;
                                admin.Packages.add(c.Package);
                                int mx = GetCompanyId(admin, c.CompanyName);
                                s.AppliedCompany.get(mx).offered = 1;
                                s.SetMaxPackageOffered(s.Id, admin);
                                for (int i = 0; i < c.StudentsOfferedCount; i++) {
                                    if (c.Students.get(i).Id == s.Id) {
                                        c.StudentsOffered.add(s);
                                        c.StudentsOfferedCount++;
                                        c.Students.get(i).offered = 1;
                                        s.CompanyOfferedCount++;
                                        s.OfferedCompany.add(c);
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("Sorry you can't apply for this role");
                            }
                        }
                    }
                    else
                        System.out.println("You chose the wrong option");
                }
                else
                    System.out.println("Companies not available");
            }

//			3) Get all available companies
            else if(choice2==3) {
                if(s.Companies.size()>0) {
                    System.out.print("List of all available Companies is as follows: ");
                    for (int i = 0; i < s.Companies.size(); i++) {
                        Company c = s.Companies.get(i);
                        int compID = c.Id;
                        String compName = c.CompanyName;
                        String compEligibility = c.eligibility;
                        if (s.CGPA == 0) {
                            System.out.println("");
                            //						System.out.println("Update your CGPA");
                        } else if (s.CGPA >= c.CGPA)
                            c.eligibility = "Available";
                        else ;
                        if(c.Registered==1) {
                            System.out.println("\n" + compID + ") CompanyName: " + compName
                                    + "\n  Company role offering: " + c.CompanyRole
                                    + "\n  Company Package: " + c.Package + " LPA"
                                    + "\n  Company CGPA Criteria: " + c.CGPA
                                    + "\n  Company Availability: " + compEligibility);
                        }
                        else
                            System.out.print("");
                    }
                }
                else
                    System.out.println("Companies not available");
            }

//			4) Get current status
            else if(choice2==4) {
                System.out.println("Status: " + s.Status);
                if(s.CompanyOfferedCount < 1)
                    System.out.println("        Unoffered");
                if(s.CompanyOfferedCount > 0) {
                    System.out.println("Following companies have rolled out offers: ");
                    for(int i=0; i<s.OfferedCompany.size(); i++) {
                        Company c = s.OfferedCompany.get(i);
                        int j = i+1;
                        System.out.println(j + "Company name: " + c.CompanyName + "\n"
                                + "Company Role: " + c.CompanyRole + "\n"
                                + "Company Package: " + c.Package + "\n");
                    }
                    int index = s.OfferedCompany.size()-1;
                    Company c = s.OfferedCompany.get(index);
                    System.out.println("You have been offered by " + c.CompanyName + "!! Please accept the offer");
                }
            }

//			5) Update CGPA
            else if(choice2==5) {
                float cgpa = sc.nextFloat();
                admin.UpdateCGPA(id,cgpa);
                for(int i=0;i<s.Companies.size();i++) {
                    Company c = s.Companies.get(i);
                    int compID = c.Id;
                    String compName = c.CompanyName;
                    String compEligibility = c.eligibility;
                    if(s.CGPA == 0) {
//						System.out.println("Update your CGPA");
                    }
                    else if(s.CGPA >= c.CGPA)
                        c.eligibility = "Available";
                    else;
                }
            }

//			6) Accept Offer
            else if(choice2 == 6) {
                if(s.OfferedCompany.size()>0) {
                    int Index = s.HighestCTCCompanyID - 1;
                    System.out.println("Congratulations " + s.Name + "!!! You have accepted the offer by " + s.OfferedCompany.get(Index).CompanyName + "!!!");
                    s.Status = "Placed";
                    s.CompanyAcceptedCount++;
                    admin.PlacedCount++;
                    admin.UnplacedCount--;
                }
                else
                    System.out.println("No companies have offered yet");
            }

//			7) Reject Offer
            else if(choice2 == 7) {
                if(s.OfferedCompany.size()>0) {
                    System.out.println("You have rejected the offer by " + s.OfferedCompany.get(0).CompanyName);
                    int compId = s.HighestCTCCompanyID;
                    s.Companies.get(compId - 1).offered = 0;
                    s.OfferedCompany.remove(0);
                    s.CompanyRejectedCount++;
                    if (s.CompanyOfferedCount == s.CompanyRejectedCount && s.CompanyAcceptedCount < 1) {
                        s.Status = "Blocked";
                        admin.BlockedCount++;
                    }
                }
                else
                    System.out.println("No companies have offered yet");
            }

//			8) Back
            else if(choice2==8)
                break;

            else
                break;
        }
    }

    //	Get Company Details inside Placement Cell Mode
    public static void GetCompanyDetails(PlacementCellMode admin, int id) {
        int index = id-1;

        Company s = admin.Companies.get(index);
        String compName = s.CompanyName;
        String compRole = s.CompanyName;
        float compPackage = s.Package;
        float compCGPA = s.CGPA;
        int compID = s.Id;
        Date compRegDate = s.RegisterationTime;

        System.out.println("Details of company " + compName + " are: ");
        System.out.println("Name: " + compName + "\n"
                + "Role Offered: " + compRole + "\n"
                + "Package Offered: " + compPackage + "\n"
                + "CGPA Required: " + compCGPA + "\n"
                + "Registered on: " + compRegDate + "\n");
        if(s.StudentsOfferedCount>0) {
            System.out.println("Students offered: ");
            for(int i=0; i<s.StudentsOfferedCount; i++) {
                Student selected = s.StudentsOffered.get(i);
                int j = i+1;
                System.out.println(j + ") Student Name: " + selected.Name + "\n"
                        + "Student RollNo: " + selected.RollNo + "\n"
                        + "Student CGPA: " + selected.CGPA + "\n"
                        + "Student Branch: " + selected.Branch + "\n");
            }
        }
    }

//	Get company id from companies arraylist of admin object

    //	Find ID of company by name
    private static int GetCompanyId(PlacementCellMode admin, String name) {
        for (int i = 0; i < admin.Companies.size(); i++) {
            Company object = admin.Companies.get(i);
            if (object !=null && object.CompanyName.equals(name)) {
                return i+1;
            }
        }
        return -1;
    }


    //	Find ID of student by rollNo
//	Get company id from companies arraylist of admin object
    private static int GetStudentId(PlacementCellMode admin, String rollno) {
        for (int i = 0; i < admin.Students.size(); i++) {
            Student object = admin.Students.get(i);
            if (object !=null && object.RollNo.equals(rollno)) {
                return i+1;
            }
        }
        return -1;
    }

//	Main function

    //	Main function
    public static void main (String argsj[]) {

//		PlacementCellMode class object - only one
        PlacementCellMode admin=new PlacementCellMode();

//		Scanner class object
        Scanner sc=new Scanner(System.in);

        while(true) {
            System.out.println("Welcome to FutureBuilder: \n"
                    + "1. Enter the Application \n"
                    + "2. Exit the Application) \n");

            int choice=sc.nextInt();

            if(choice==1) {
                while(true) {
                    System.out.println("1) Enter as Student Mode \n"
                            + "2) Enter as Company Mode \n"
                            + "3) Enter as Placement Cell Mode \n"
                            + "4) Return To Main Application");
                    choice=sc.nextInt();

//				Student Mode
                    if(choice==1) {
                        StudentOptions(admin);
                    }

//				Company Mode
                    else if(choice==2) {
                        CompanyOptions(admin);
                    }

//				Placement Cell Mode
                    else if(choice ==3) {
                        PlacementCellOptions(admin);
                    }

//				Return to Main Application
                    else if(choice==4)
                        break;

                    else
                        System.out.println("Invalid Choice");

                }

            }

            else if(choice==2) {
                System.out.println("Thanks for using FutureBuilder!!!!");
                System.exit(0);
            }

            else
                System.out.println("Invalid Choice");
        }

    }
}