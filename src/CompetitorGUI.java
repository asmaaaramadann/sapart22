import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class CompetitorGUI {
    private JFrame frame;
    private CompetitorList competitorList;
    private JTable competitorTable;

    public CompetitorGUI() {
        this.competitorList = new CompetitorList();

        frame = new JFrame("Competitor Management");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a table model with your data
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Competitor Number");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Level");
        tableModel.addColumn("Country");
        tableModel.addColumn("Score 1");
        tableModel.addColumn("Score 2");
        tableModel.addColumn("Score 3");
        tableModel.addColumn("Score 4");
        tableModel.addColumn("Score 5");


        // Populate the table with data from the competitorList
        for (Competitor competitor : competitorList.getCompetitors()) {
            Object[] rowData = {
                    competitor.getCompetitorNumber(),
                    competitor.getCompetitorFname(),
                    competitor.getCompetitorLname(),
                    competitor.getAge(),
                    competitor.getGender(),
                    competitor.getLevel(),
                    competitor.getCountry(),
                    competitor.getScores()[0],
                    competitor.getScores()[1],
                    competitor.getScores()[2],
                    competitor.getScores()[3],
                    competitor.getScores()[4]
                    // ... Add more data as needed
            };
            tableModel.addRow(rowData);
        }

        // Create the JTable with the model
        competitorTable = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane tableScrollPane = new JScrollPane(competitorTable);

        JButton addButton = new JButton("Add Competitor");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCompetitorDialog();
            }
        });

        JButton removeButton = new JButton("Remove Competitor");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedCompetitorNumber = getSelectedCompetitorNumber();
                if (selectedCompetitorNumber != -1) {
                    competitorList.removeCompetitor(selectedCompetitorNumber);
                    updateTableData();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a competitor to remove.");
                }
            }
        });



        JButton amendButton = new JButton("Ammend Competitor");
        amendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Competitor selectedCompetitor = getSelectedCompetitor();
                if (selectedCompetitor != null) {
                    showEditCompetitorDialog(selectedCompetitor);
                    updateTableData();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a competitor to amend.");
                }
            }
        });


        JButton printButton = new JButton("Print Full Details");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printFullDetails();
            }
        });

        JButton reportButton = new JButton("Generate Report");
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
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
        buttonPanel.add(removeButton);
        buttonPanel.add(amendButton);
        buttonPanel.add(printButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(closeButton);

        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);


        frame.setVisible(true);
    }

    public int getSelectedCompetitorNumber() {
        int selectedRow = competitorTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get the competitor number from the selected row
            return (int) competitorTable.getValueAt(selectedRow, 0);
        } else {
            return -1; // Return a sentinel value or handle it according to your needs
        }
    }


    public Competitor getSelectedCompetitor() {
        int selectedRow = competitorTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get the competitor number from the selected row
            int competitorNumber = (int) competitorTable.getValueAt(selectedRow, 0);
            // Retrieve the competitor from the list based on the competitor number
            return competitorList.getCompetitorByNumber(competitorNumber);
        } else {
            return null;
        }
    }


    private void updateTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) competitorTable.getModel();
        tableModel.setRowCount(0);

        for (Competitor competitor : competitorList.getCompetitors()) {
            Object[] rowData = {
                    competitor.getCompetitorNumber(),
                    competitor.getCompetitorFname(),
                    competitor.getCompetitorLname(),
                    competitor.getAge(),
                    competitor.getGender(),
                    competitor.getLevel(),
                    competitor.getCountry(),
                    competitor.getScores()[0],
                    competitor.getScores()[1],
                    competitor.getScores()[2],
                    competitor.getScores()[3],
                    competitor.getScores()[4]

            };
            tableModel.addRow(rowData);
        }
    }

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

            // Update the table data
            updateTableData();
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
                // Update the competitor details
                competitor.setCompetitorFname(fNameField.getText());
                competitor.setCompetitorLname(lNameField.getText());
                competitor.setAge(Integer.parseInt(ageField.getText()));
                competitor.setGender(genderField.getText());
                competitor.setLevel(levelField.getText());
                competitor.setCountry(countryField.getText());

                // Close the dialog
                editDialog.setVisible(false);

                // Update the table data
                updateTableData();
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



    private void printFullDetails() {
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);

        for (Competitor competitor : competitorList.getCompetitors()) {
            detailsArea.append(competitor.getFullDetails() + "\n\n");
        }

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(frame, scrollPane, "Competitor Details", JOptionPane.PLAIN_MESSAGE);
    }


    private void generateReport() {
        StringBuilder report = new StringBuilder();

        // Top overall score
        report.append("Top Overall Score: ").append(findTopOverallScore()).append("\n");

        // Average overall score
        report.append("Average Overall Score: ").append(calculateOverallAverage()).append("\n");

        // Maximum overall scores
        report.append("Maximum Overall Scores: ").append(scoresToString(findMaxOverallScores())).append("\n");

        // Minimum overall score
        report.append("Minimum Overall Score: ").append(findMinOverallScore()).append("\n\n");

        // Frequency report
        report.append("Frequency Report:\n").append(generateFrequencyReport()).append("\n");

        JTextArea reportArea = new JTextArea(report.toString());
        reportArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(frame, scrollPane, "Competitor Report", JOptionPane.PLAIN_MESSAGE);
    }

    private String findTopOverallScore() {
        return "Top Overall Score Placeholder";
    }

    private double calculateOverallAverage() {
        return 0.0;
    }

    private ArrayList<Integer> findMaxOverallScores() {
        return new ArrayList<>();
    }

    private String findMinOverallScore() {
        return "Minimum Overall Score Placeholder";
    }

    private String generateFrequencyReport() {
        return "Frequency Report Placeholder";
    }

    private String scoresToString(ArrayList<Integer> list) {
        return "Scores Placeholder";
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
