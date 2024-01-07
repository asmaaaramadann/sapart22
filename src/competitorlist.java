import java.io.*;
import java.util.ArrayList;

public class competitorlist {

    private ArrayList<Competitor> competitorsList;

    public competitorlist() {
        this.competitorsList = new ArrayList<>();
    }

    private String listCompetitorsDetails() {
        StringBuilder result = new StringBuilder();

        for (Competitor c : competitorsList) {
            result.append(c.getFullDetails()).append("\n");
        }

        return result.toString();
    }

}
