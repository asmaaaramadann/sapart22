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
    public void addCompetitor(Competitor competitor) {                         // adds competitor by taking the object of a competitor and adding using add keyword in array list
        competitors.add(competitor);
    }

    public void removeCompetitor(Competitor competitors) {
        competitors.remove(competitors);
    }   // removes competitor by taking the object of a competitor and adding using remove keyword in array list


    public void ammendCompetitors(Competitor competitor, Competitor newDetails) {          // ammend method to edit a competitor
        if (competitors.contains(competitor)) {                                         //checks if the competitor is in the list
            competitors.remove(competitor);                                              // if they r in the list then remove their current oobject and add a new one with edited details
            competitors.add(newDetails);
        } else {
            System.out.println("competitor not found ");                          // if not in list prints  the message
        }

    }


    public String printCompetitorsList() {
        StringBuilder result = new StringBuilder();

        for (Competitor competitor : competitors) {
            result.append(competitor.getFullDetails()).append("\n");
        }

        return result.toString();
    }

}
