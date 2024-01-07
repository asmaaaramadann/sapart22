import java.util.ArrayList;

public class Manager {

    private int managerID;
    private String managerFname;
    private String managerLname;
    private ArrayList<Competitor> competitors;

    public Manager(int managerID, String managerFname, String managerLname) {
        this.managerID = managerID;
        this.managerFname = managerFname;
        this.managerLname = managerLname;
        this.competitors = new ArrayList<>();
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getManagerFname() {
        return managerFname;
    }

    public void setManagerFname(String managerFname) {
        this.managerFname = managerFname;
    }

    public String getManagerLname() {
        return managerLname;
    }

    public void setManagerLname(String managerLname) {
        this.managerLname = managerLname;
    }

    public String getCompetitorById(int id) {
        for (Competitor competitor : competitors) {
            int competitorId = competitor.getCompetitorNumber();
            if (competitorId == id) {
                return competitor.getShortDetails();
            }
        }
        return "Competitor not found";
    }

    public String printCompetitorsList() {
        StringBuilder result = new StringBuilder();

        for (Competitor competitor : competitors) {
            result.append(competitor.getFullDetails()).append("\n");
        }

        return result.toString();
    }
}
