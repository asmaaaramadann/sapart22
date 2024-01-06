import java.util.ArrayList;

public class Manager {

    private int managerID;

    private  String managerFname;
    private String managerLname;

    ArrayList<Competitor> competitors;


    public Manager(int managerId, String managerFname, String managerLname) {
        this.managerID = managerId;
        this.managerFname = managerFname;
        this.managerLname = managerLname;
        competitors = new ArrayList<>();
    }


    public int getManagerId() {
        return managerID;
    }
    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    public String managerFname() {
        return managerFname ;
    }
    public void setManagerFname(String managerFname) {
        this.managerFname = managerFname;
    }

    public String getManagerLname() {
        return managerLname ;
    }
    public void setManagerLname(String managerLname) {
        this.managerLname = managerLname;
    }


    public String getCompetitorById(int id) {
        for (Competitor competitor : competitors) {                      // finds a certain competitor by giving the competitor an id in the params
            int competitorId = competitor.getCompetitorNumber();              // this is similar to the method isInlist but instead will return the short details instead of boolean
            if (competitorId == id) {
                return competitor.getShortDetails();
            }

        }
        return null;
    }

    public String printCompetitorsList() {
        StringBuilder result = new StringBuilder();

        for (Competitor competitor : competitors) {
            result.append(competitor.getFullDetails()).append("\n");
        }

        return result.toString();
    }

}



















