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
        // Remove competitor by competitor number
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

    public void readAndInsertFromCsvFile(String filePath) throws IOException, Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String data;
            while ((data = br.readLine()) != null) {
                String[] values = data.split(",");
                int competitorNumber = Integer.parseInt(values[0]);
                String competitorFname = values[1];
                String competitorLname = values[2];
                int age = Integer.parseInt(values[3]);
                String gender = values[4];
                String level = values[5];
                String country = values[6];
                int score1 = Integer.parseInt(values[7]);
                int score2 = Integer.parseInt(values[8]);
                int score3 = Integer.parseInt(values[9]);
                int score4 = Integer.parseInt(values[10]);
                int score5 = Integer.parseInt(values[11]);
                int[] scores = {score1, score2, score3, score4, score5};

                Competitor c = new Competitor(competitorNumber, competitorFname, competitorLname, age, gender, level, country, scores);
                addCompetitor(c);
            }
        }
    }

    public void writeToFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Competitor competitor : competitorsList) {
                if (competitor != null) {
                    writer.write(competitor.getFullDetails() + "\n");
                }
            }

            // Write top overall score
            writer.write("Top overall score: " + competition.findTopOverallScore() + "\n");

            // Write average overall scores
            writer.write("Average Overall scores: " + competition.calculateOverallAverage() + "\n");

            // Write maximum overall scores
            ArrayList<Integer> maxOverallScoresList = competition.findMaxOverallScores();
            writer.write("Maximum Overall scores are " + competition.scoresToString(maxOverallScoresList) + "\n");

            // Write minimum overall score
            writer.write("Minimum Overall score is " + competition.findMinOverallScore() + "\n\n");

            // Write frequency report
            writer.write("Frequency report:\n" + competition.generateFrequencyReport() + "\n");
        }
    }
    

   
}