import java.util.ArrayList;

public class Competition {
    public int competitionsID;
    public String competitionName;
    public ArrayList<Competitor> competitors;


    public Competition(int competitionsID, String competitionName)  {
        this.competitionsID =  competitionsID;
        this.competitionName = competitionName;
        this.competitors = new ArrayList<>();

    }
}