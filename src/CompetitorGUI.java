import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CompetitorGUI {
    private JFrame frame;
    private competitorList competitorList;

    public CompetitorGUI() {
        competitorList = new competitorList(); // You need to implement the CompetitorList class.

        frame = new JFrame("Competitor Management");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addButton = new JButton("Add Competitor");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCompetitorDialog();
            }
        });

        JButton editButton = new JButton("Edit Competitor");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Competitor selectedCompetitor = getSelectedCompetitor();
                if (selectedCompetitor != null) {
                    showEditCompetitorDialog(selectedCompetitor);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a competitor to edit.");
                }
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    competitorList.writeToFile("competitor_report.txt");
                    JOptionPane.showMessageDialog(frame, "Competitor report saved to competitor_report.txt");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error saving competitor report");
                }
                frame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(closeButton);

        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.NORTH);
        // Add other components and panels...

        frame.setVisible(true);
    }

//    private Competitor getSelectedCompetitor() {
//
//    }


    private void showAddCompetitorDialog() {
        JTextField fNameField = new JTextField();
        JTextField lNameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField levelField = new JTextField();
        JTextField countryField = new JTextField();
        JTextField score1Field = new JTextField();
        JTextField score2Field = new JTextField();
        JTextField score3Field = new JTextField();
        JTextField score4Field = new JTextField();
        JTextField score5Field = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("First Name:"));
        panel.add(fNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lNameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);
        panel.add(new JLabel("Level:"));
        panel.add(levelField);
        panel.add(new JLabel("Country:"));
        panel.add(countryField);
        panel.add(new JLabel("Score 1:"));
        panel.add(score1Field);
        panel.add(new JLabel("Score 2:"));
        panel.add(score2Field);
        panel.add(new JLabel("Score 3:"));
        panel.add(score3Field);
        panel.add(new JLabel("Score 4:"));
        panel.add(score4Field);
        panel.add(new JLabel("Score 5:"));
        panel.add(score5Field);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Competitor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Get data from fields and add competitor to the list
            String fName = fNameField.getText();
            String lName = lNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String level = levelField.getText();
            String country = countryField.getText();
            int score1 = Integer.parseInt(score1Field.getText());
            int score2 = Integer.parseInt(score2Field.getText());
            int score3 = Integer.parseInt(score3Field.getText());
            int score4 = Integer.parseInt(score4Field.getText());
            int score5 = Integer.parseInt(score5Field.getText());

            // Create a new competitor and add to the list
            int[] scores = {score1, score2, score3, score4, score5};
            Competitor newCompetitor = new Competitor(competitorList.getNextCompetitorNumber(), fName, lName, age, gender, level, country, scores);
            competitorList.addCompetitor(newCompetitor);
        }
    }



    private void showEditCompetitorDialog(Competitor competitor) {
        JDialog editDialog = new JDialog(frame, "Edit Competitor", true);
        editDialog.setSize(400, 300);
        editDialog.setLayout(new BorderLayout());

        JTextField fNameField = new JTextField(competitor.getCompetitorFname());
        JTextField lNameField = new JTextField(competitor.getCompetitorLname());
        JTextField ageField = new JTextField(String.valueOf(competitor.getAge()));
        JTextField genderField = new JTextField(competitor.getGender());
        JTextField levelField = new JTextField(competitor.getLevel());
        JTextField countryField = new JTextField(competitor.getCountry());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newFName = fNameField.getText();
                String newLName = lNameField.getText();
                int newAge = Integer.parseInt(ageField.getText());
                String newGender = genderField.getText();
                String newLevel = levelField.getText();
                String newCountry = countryField.getText();

                int[] newScores = new int[5];

                competitor.updateDetails(newFName, newLName, newAge, newGender, newLevel, newCountry, newScores);

                editDialog.dispose();
                // You may call a method to update the table or display
                // For example, updateCompetitorsTable();
            }
        });

        editDialog.add(fNameField, BorderLayout.NORTH);
        editDialog.add(lNameField, BorderLayout.CENTER);
        editDialog.add(ageField, BorderLayout.CENTER);
        editDialog.add(genderField, BorderLayout.CENTER);
        editDialog.add(levelField, BorderLayout.CENTER);
        editDialog.add(countryField, BorderLayout.CENTER);
        editDialog.add(saveButton, BorderLayout.SOUTH);

        editDialog.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CompetitorGUI();
            }
        });
    }
}
