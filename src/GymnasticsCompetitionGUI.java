import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class GymnasticsCompetitionGUI {
    private Competition gymnastics;
    private JFrame frame;
    private JTextArea listing;
    private JButton clearButton;

    public GymnasticsCompetitionGUI() throws IOException {
        gymnastics = new Competition("Gymnastics", 1001);
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Gymnastics Competition System");
        frame.setLayout(new BorderLayout());

        listing = new JTextArea();
        frame.add(listing, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(1, 1));

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        eastPanel.add(clearButton);

        frame.add(eastPanel, BorderLayout.EAST);

        frame.pack();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateListing(String text) {
        listing.setText(text);
    }

    private Competitor createCompetitor() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new Competitor ID:"));
        String fName = JOptionPane.showInputDialog(frame, "Enter Competitor's First Name:");
        String lName = JOptionPane.showInputDialog(frame, "Enter Competitor's Last Name:");
        String gender = JOptionPane.showInputDialog(frame, "Enter Competitor's Gender:");
        int age = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Competitor's Age:"));
        String country = JOptionPane.showInputDialog(frame, "Enter Competitor's Country:");

        // You might have specific fields for a gymnast like apparatus or routine information
        String apparatus = JOptionPane.showInputDialog(frame, "Enter Competitor's Apparatus:");
        int[] scores;

        do {
            String input = JOptionPane.showInputDialog(frame, "Enter Competitor's Scores in format 5 5 5 5 5");

            if (input != null && !input.isEmpty()) {
                scores = Arrays.stream(input.split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter at least one score.");
                scores = null;
            }
        } while (scores == null);

        // Instantiate and return a Competitors object with the collected information
        return new Competitor(id, fName, lName, age, gender, country, apparatus, scores);
    }

    private class RemoveCompetitor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputValue = JOptionPane.showInputDialog(frame, "Enter competitor number to remove:");
            int competitorId = Integer.parseInt(inputValue);
            gymnastics.removeCompetitor(competitorId);
            JOptionPane.showMessageDialog(frame, "Competitor removed successfully!");
        }
    }

    private class AmendCompetitorDetails implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputValue = JOptionPane.showInputDialog(frame, "Enter competitor number to amend details:");
            int competitorId = Integer.parseInt(inputValue);
            Competitor existingCompetitor = gymnastics.getCompetitorById(competitorId);

            if (existingCompetitor != null) {
                Competitor newCompetitor = createCompetitor();
                gymnastics.amendCompetitor(existingCompetitor, newCompetitor);
                JOptionPane.showMessageDialog(frame, "Competitor details amended successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found");
            }
        }
    }

    private class ViewScores implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputValue = JOptionPane.showInputDialog(frame, "Enter competitor number to view scores:");
            int competitorId = Integer.parseInt(inputValue);
            Competitor competitor = gymnastics.getCompetitorById(competitorId);

            if (competitor != null) {
                JOptionPane.showMessageDialog(frame, "Competitor Scores:\n" + Arrays.toString(competitor.getScores()));
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found");
            }
        }
    }

    private class GenerateReport implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String report = gymnastics.generateReport();
            JOptionPane.showMessageDialog(frame, "Generated Report:\n" + report);
        }
    }


    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listing.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GymnasticsCompetitionGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
