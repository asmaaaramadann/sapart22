import java.io.IOException;
import java.util.ArrayList;

public class Competition {

    private String competitionName;
    private int competitionID;

    private ArrayList<Competitor> competitorsList;
    private ArrayList<Staff> staffList;


    public Competition(String competitionName, int competitionID) throws IOException {
        this.competitionName = competitionName;
        this.competitionID = competitionID;
        this.competitorsList = new ArrayList<>();
        this.staffList = new ArrayList<>();
        setupCompetitors();
        setupStaff();
    }

    private void setupCompetitors() throws IOException {
        CompetitorList competitorList = new CompetitorList();
        competitorList.readAndInsertFromCsvFile("C:\\Users\\Asmaaa Ramadan\\IdeaProjects\\sapart22\\RunCompetitor.csv");

        // Adding some default competitors
        for (int i = 1; i <= 11; i++) {
            Competitor competitor = new Competitor(i, "a", "b", 20, "female", "amateur", "UK", new int[]{2, 3, 4, 1, 5});
            competitorsList.add(competitor);
        }
    }

    private void setupStaff() {
        Staff staff1 = new Staff(300, 100);
        Staff staff2 = new Staff(301, 102);
        staffList.add(staff1);
        staffList.add(staff2);
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public int getCompetitionID() {
        return competitionID;
    }

    public void setCompetitionID(int competitionID) {
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


    public void addCompetitor(Competitor competitor) {
    }
    public static void main(String[] args) {
        try {
            Competition competition = new Competition("YourCompetitionName", 123);
            // Perform other actions or invoke GUI here...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



