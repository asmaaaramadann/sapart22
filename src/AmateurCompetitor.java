public class AmateurCompetitor extends Competitor {
    private int[] amateurScores;

    public AmateurCompetitor(int competitorNumber, String competitorFname, String competitorLname,
                             int age, String gender, String level, String country, int[] scores) {
        super(competitorNumber, competitorFname, competitorLname, age, gender, level, country, scores);
        this.amateurScores = scores;
    }

    @Override
    public int[] getScores() {
        return amateurScores;
    }
}
