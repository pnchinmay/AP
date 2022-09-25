package placement;
//import java.text.SimpleDateFormat;
import java.util.*;
public class CompanyMode {
    public String CompanyName;
    public String CompanyRole ;
    public float Package;
    public float CGPA ;
    public int Id;
    public int Choice;
    public Date RegisterationTime;

    Scanner sc=new Scanner(System.in);

    public CompanyMode() {}
    public CompanyMode(String CompanyName,String CompanyRole,float Package,float CGPA, int id) {
        this.CompanyName=CompanyName;
        this.CompanyRole=CompanyRole;
        this.Package=Package;
        this.CGPA=CGPA;
        this.Id = id;
    }

    public void SetDetails(PlacementCellMode admin) {
        String name=sc.nextLine();
        String role=sc.nextLine();
        float ctc=sc.nextFloat();
        float gpa=sc.nextFloat();
//		Date Time=Calendar.getInstance().getTime();

        this.Id=admin.CompanyCount+1;
        this.CompanyName=name;
        this.CompanyRole=role;
        this.Package=ctc;
        this.CGPA=gpa;
//		this.RegisterationTime=Time;

        admin.CompanyCount++;
        int index = admin.CompanyCount-1;
        System.out.println("Hello");
        admin.Companies.add(new Company());
        admin.Companies.get(index).CompanyName = name;
        admin.Companies.get(index).CompanyRole = role;
        admin.Companies.get(index).Package = ctc;
        admin.Companies.get(index).CGPA = gpa;
        admin.Companies.get(index).Id = index+1;
//		admin.Companies.get(Id).RegisterationTime = this.RegisterationTime;

        GetDetailsArray(Id, admin);
    }

    public void GetDetailsArray(int id, PlacementCellMode admin) {

        Company x = new Company();
        id--;
        x = admin.Companies.get(id);
        String name = x.CompanyName;
        String role = x.CompanyRole;
        float ctc = x.Package;
        float gpa = x.CGPA;
        Date time = x.RegisterationTime;
        System.out.println("Here are the details for Id: "+ id);
        System.out.println(name+ " " + role + " " + ctc + " " + gpa + " " + id + " " + time + "\n");
    }
}
