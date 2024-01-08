import java.io.*;
import java.util.ArrayList;

public class Competition {

    private String competitionName;
    private int competitionID;

    private ArrayList<Competitor> competitorsList;

    public Competition(String competitionName, int competitionID) {
       this.competitionName = competitionName;
       this.competitionID = competitionID;
        this.competitorsList = new ArrayList<>();
    }
    private void setupCompetitors() throws IOException{
        Competitor competitor1 = new Competitor(1, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor2 = new Competitor(2, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor3 = new Competitor(3, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor4 = new Competitor(4, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor5 = new Competitor(5, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor6 = new Competitor(6, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor7 = new Competitor(7, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor8 = new Competitor(8, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor9 = new Competitor(9, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor10 = new Competitor(10, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );
        Competitor competitor11 = new Competitor(11, "a", "b", 20,"female", "amateur", "UK", new int[]{2,3,4,1,5} );

        competitorsList.add(competitor1);
        competitorsList.add(competitor2);
        competitorsList.add(competitor3);
        competitorsList.add(competitor4);
        competitorsList.add(competitor5);
        competitorsList.add(competitor6);
        competitorsList.add(competitor7);
        competitorsList.add(competitor8);
        competitorsList.add(competitor9);
        competitorsList.add(competitor10);
        competitorsList.add(competitor11);
        CompetitorList.readAndInsertFromCsvFile("C:\\Users\\Asmaaa Ramadan\\IdeaProjects\\sapart22\\RunCompetitor.csv");


    }
    public String getcompetitionName() {
        return competitionName;
    }
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public int getCompetitionID(){
        return competitionID;
    }

    public void setCompetitionID(int competitionID){
        this.competitionID = competitionID;
    }

    private String listCompetitorsDetails() {
        StringBuilder result = new StringBuilder();

        for (Competitor c : competitorsList) {
            result.append(c.getFullDetails()).append("\n");
        }

        return result.toString();
    }

    private String findTopOverallScore() {
        if (competitorsList.isEmpty()) {
            return null;
        }

        double topScore = 0;
        String topScorerDetails = "";

        for (Competitor c : competitorsList) {
            double overallScore = c.getOverallScore();
            if (overallScore > topScore) {
                topScore = overallScore;
                topScorerDetails = c.getFullDetails();
            }
        }

        return String.format("%.2f by %s", topScore, topScorerDetails);
    }

    private double calculateTotalOverallScores() {
        double sum = 0;

        for (Competitor c : competitorsList) {
            sum += c.getOverallScore();
        }

        return sum;
    }

    private double calculateOverallAverage() {
        double sum = calculateTotalOverallScores();
        double average = sum / competitorsList.size();
        return Math.round(average * 100.0) / 100.0;
    }

    private ArrayList<Integer> findMaxOverallScores() {
        ArrayList<Integer> maxScores = new ArrayList<>();

        for (Competitor c : competitorsList) {
            int max = Integer.MIN_VALUE;
            for (int score : c.getScores()) {
                if (score > max) {
                    max = score;
                }
            }
            maxScores.add(max);
        }

        return maxScores;
    }

    private String scoresToString(ArrayList<Integer> list) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            if (i < list.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    private String findMinOverallScore() {
        double min = Double.POSITIVE_INFINITY;
        String minScorerDetails = "";

        for (Competitor c : competitorsList) {
            double overallScore = c.getOverallScore();
            if (overallScore < min) {
                min = overallScore;
                minScorerDetails = c.getFullDetails();
            }
        }

        return String.format("%.2f by %s", min, minScorerDetails);
    }

    private String generateFrequencyReport() {
        int[] scoresFrequency = new int[6];

        for (Competitor c : competitorsList) {
            for (int score : c.getScores()) {
                scoresFrequency[score]++;
            }
        }

        StringBuilder report = new StringBuilder("Scores Frequency Table:\n");
        for (int i = 0; i < scoresFrequency.length; i++) {
            report.append("Score ").append(i).append(": ").append(scoresFrequency[i]).append("\n");
        }

        return report.toString();
    }


    public void removeCompetitor(int competitorId) {
    }

    public Competitor getCompetitorById(int competitorId) {
        return null;
    }

    public void amendCompetitor(Competitor existingCompetitor, Competitor newCompetitor) {}

    public String generateReport() {
        return null;
    }

    public void addCompetitor(Competitor competitor) {
    }
}



