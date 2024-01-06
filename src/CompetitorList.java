import java.io.*;
import java.util.ArrayList;

public class CompetitorList {

    private static String listCompetitorsDetails(ArrayList<Competitor> competitors) {
        StringBuilder result = new StringBuilder();

        for (Competitor c : competitors) {
            result.append(c.getFullDetails()).append("\n");
        }

        return result.toString();
    }

    private static String findTopOverallScore(ArrayList<Competitor> competitors) {
        if (competitors.isEmpty()) {
            return null;
        }

        double topScore = 0;
        String topScorerName = " ";

        for (Competitor c : competitors) {
            double overallScore = c.getOverallScore(c.getScores());
            if (overallScore > topScore) {
                topScore = overallScore;
                topScorerName = "ID is: " + Integer.toString(c.getCompetitorNumber()) + "Name: " + c.getCompetitorFname() + c.getCompetitorLname();
            }
        }

        return String.format("%.2f by %s", topScore, topScorerName);
    }

    private static double calculateTotalOverallScores(ArrayList<Competitor> competitors) {
        double sum = 0;

        for (Competitor c : competitors) {
            double overallScore = c.getOverallScore(c.getScores());
            sum += overallScore;
        }

        return sum;
    }

    private static double calculateOverallAverage(ArrayList<Competitor> competitors) {
        double sum = calculateTotalOverallScores(competitors);
        double average = sum / competitors.size();
        return Math.round(average * 100.0) / 100.0;
    }

    private static ArrayList<Integer> findMaxOverallScores(ArrayList<Competitor> competitors) {
        ArrayList<Integer> maxScores = new ArrayList<>();

        for (Competitor c : competitors) {
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

    private static String scoresToString(ArrayList<Integer> list) {
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

    private static String findMinOverallScore(ArrayList<Competitor> competitors) {
        double min = Double.POSITIVE_INFINITY;
        String minScorerName = "";

        for (Competitor c : competitors) {
            double overallScore = c.getOverallScore(c.getScores());
            if (overallScore < min) {
                min = overallScore;
                minScorerName = "ID is: " + Integer.toString(c.getCompetitorNumber()) + "Name: " + c.getCompetitorFname() + c.getCompetitorLname();
            }
        }

        return String.format("%.2f by %s", min, minScorerName);
    }

    private static String generateFrequencyReport(ArrayList<Competitor> competitors) {
        int[] scoresFrequency = new int[6];

        for (Competitor c : competitors) {
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

    public static void readAndInsertFromCsvFile(String filePath, ArrayList<Competitor> competitors) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String data;
            while ((data = br.readLine()) != null) {
                try {
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
                    competitors.add(c);

                } catch (Exception e) {
                    System.out.println("Error processing line: " + data);
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }
    }

    public static void writeToFile(String filePath, ArrayList<Competitor> competitors) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(listCompetitorsDetails(competitors) + "\n");
            writer.write("Top overall score is " + findTopOverallScore(competitors) + "\n");
            writer.write("Average Overall scores are " + calculateOverallAverage(competitors) + "\n");
            ArrayList<Integer> maxOverallScoresList = findMaxOverallScores(competitors);
            writer.write("Maximum Overall scores are " + scoresToString(maxOverallScoresList) + "\n");
            writer.write("Minimum Overall score is " + findMinOverallScore(competitors) + "\n" + "\n");
            writer.write("Frequency report:\n" + generateFrequencyReport(competitors) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}