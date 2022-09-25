package placement;

import java.util.ArrayList;
import java.util.Date;


public class PlacementCellMode {

    //	Company count with default value 0
    public int CompanyCount=0;

    //	Student count with default value 0
    public int StudentCount=0;
    public int PlacedCount=0;
    public int UnplacedCount=0;
    public int BlockedCount=0;
    public int OffersCount=0;

    //	Date array to store company and student registration start and ending date
    public Date[] RegistrationDates = new Date[4];

    public ArrayList<Company> Companies= new ArrayList<Company>(5);
    public ArrayList<Student> Students= new ArrayList<Student>(5);
    public ArrayList<Float> Packages= new ArrayList<Float>(5);

    public void UpdateCGPA(int id, float cgpa) {
        this.Students.get(id-1).CGPA = cgpa;
    }
}
