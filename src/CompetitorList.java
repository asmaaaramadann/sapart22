import java.io.*;
import java.util.ArrayList;

public class CompetitorList {
    private static ArrayList<Competitor> competitorsList;

    public CompetitorList() {
        this.competitorsList = new ArrayList<>();
    }

    public static void addCompetitor(Competitor competitor) {
        competitorsList.add(competitor);
    }

    public void removeCompetitor(int competitorNumber) {
        Competitor toRemove = null;
        for (Competitor competitor : competitorsList) {
            if (competitor.getCompetitorNumber() == competitorNumber) {
                toRemove = competitor;
                break;
            }
        }
        if (toRemove != null) {
            competitorsList.remove(toRemove);
        }
    }
    public static void readAndInsertFromCsvFile(String filePath) throws IOException, NumberFormatException {
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
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            throw e;
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
            writer.write("Top overall score: " + findTopOverallScore() + "\n");

            // Write average overall scores
            writer.write("Average Overall scores: " + calculateOverallAverage() + "\n");

            // Write maximum overall scores
            ArrayList<Integer> maxOverallScoresList = findMaxOverallScores();
            writer.write("Maximum Overall scores: " + scoresToString(maxOverallScoresList) + "\n");

            // Write minimum overall score
            writer.write("Minimum Overall score: " + findMinOverallScore() + "\n\n");

            // Write frequency report
            writer.write("Frequency report:\n" + generateFrequencyReport() + "\n");
        }
    }

    private String findTopOverallScore() {
        if (competitorsList.size() == 0) {
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

    private double calculateOverallAverage() {
        double sum = calculateTotalOverallScores();

        int numberOfCompetitors = competitorsList.size();
        if (numberOfCompetitors == 0) {
            return 0.0;
        }

        double average = sum / numberOfCompetitors;
        return Math.round(average * 100.0) / 100.0;
    }

    private double calculateTotalOverallScores() {
        double sum = 0;

        for (Competitor c : competitorsList) {
            sum += c.getOverallScore();
        }

        return sum;
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

    public int getNextCompetitorNumber() {
        int maxCompetitorNumber = 0;

        for (Competitor competitor : competitorsList) {
            int currentCompetitorNumber = competitor.getCompetitorNumber();
            if (currentCompetitorNumber > maxCompetitorNumber) {
                maxCompetitorNumber = currentCompetitorNumber;
            }
        }

        return maxCompetitorNumber + 1;
    }
}
