public class ProfessionalCompetitor extends Competitor {
            private int[] scores;

            public ProfessionalCompetitor(int competitorNumber, String competitorFname, String competitorLname,
                                          int age, String gender, String level, String country, int[] scores) {
                super(competitorNumber, competitorFname, competitorLname, age, gender, level, country, scores);
                this.scores = scores;
            }
        }
