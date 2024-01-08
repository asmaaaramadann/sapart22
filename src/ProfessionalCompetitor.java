public class ProfessionalCompetitor extends Competitor {
    private int[] professionalScores;

    public ProfessionalCompetitor(int competitorNumber, String competitorFname, String competitorLname,
                                  int age, String gender, String level, String country, int[] scores) {
        super(competitorNumber, competitorFname, competitorLname, age, gender, level, country, scores);
        this.professionalScores = scores;
    }

    @Override
    public int[] getScores() {
        return professionalScores;
    }
}
