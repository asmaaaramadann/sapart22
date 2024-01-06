public class Staff {
    private int staffID;
    private Competitor[] competitors;


    public Staff(int staffID) {
        this.staffID = staffID;
    }
    public int getstaffID() {
        return staffID;
    }
    public void setstaffID(int staffID) {
        this.staffID = staffID;
    }

    public void addCompetitor(Competitor competitor) {
        competitor.add(competitor);
    }

    public void removeCompetitors(Competitor competitors) {
        competitors.remove(competitors);
    }

    public void recordCompetitorDetails(int competitorNumber, String competitorFname, String competitorLname,
                                        int age, String gender, String level, String country, int[] scores) {
        Competitor newCompetitor = new Competitor(competitorNumber, competitorFname, competitorLname,
                age, gender, level, country, scores);

        addCompetitor(newCompetitor);
    }
    public String requestResults(int competitorNumber) {
        for (Competitor competitor : competitors) {
            if (competitor != null && competitor.getCompetitorNumber() == competitorNumber) {
                return competitor.getFullDetails(); // Or any other relevant information you want to retrieve
            }
        }
        return "Competitor not found";
    }

    public void ammendCompetitorDetails(Competitor competitor, Competitor newDetails) {
        if (competitor.contains(competitor)) {
            competitor.remove(competitor);
            competitor.add(newDetails);
        } else {
            System.out.println(" competitor is not in the list ");
        }



    }
}
