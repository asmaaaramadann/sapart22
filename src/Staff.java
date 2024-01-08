import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Staff {
    private int staffID;
    private Competitor[] competitors;

    public Staff(int staffID, int maxCompetitors) {
        this.staffID = staffID;
        this.competitors = new Competitor[maxCompetitors];
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public void addCompetitor(Competitor competitor) {
        for (int i = 0; i < competitors.length; i++) {
            if (competitors[i] == null) {
                competitors[i] = competitor;
                return;
            }
        }
        System.out.println("Competitor array is full. Cannot add more competitors.");
    }

    public void removeCompetitor(int competitorNumber) {
        for (int i = 0; i < competitors.length; i++) {
            if (competitors[i] != null && competitors[i].getCompetitorNumber() == competitorNumber) {
                competitors[i] = null;
                return;
            }
        }
        System.out.println("Competitor with number " + competitorNumber + " not found.");
    }

    public String requestResults(int competitorNumber) {
        for (Competitor competitor : competitors) {
            if (competitor != null && competitor.getCompetitorNumber() == competitorNumber) {
                return competitor.getFullDetails();
            }
        }
        return "Competitor not found";
    }

    public void amendCompetitorDetails(int competitorNumber, Competitor newDetails) {
        for (int i = 0; i < competitors.length; i++) {
            if (competitors[i] != null && competitors[i].getCompetitorNumber() == competitorNumber) {
                competitors[i] = newDetails;
                return;
            }
        }
        System.out.println("Competitor with number " + competitorNumber + " not found.");
    }

    public void recordCompetitorDetails(int competitorNumber, String competitorFname, String competitorLname,
                                        int age, String gender, String level, String country, int[] scores) {
        Competitor newCompetitor = new Competitor(competitorNumber, competitorFname, competitorLname,
                age, gender, level, country, scores);
        addCompetitor(newCompetitor);
    }



    public void writeToFile(String file) {
    }
}
