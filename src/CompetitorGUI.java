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

        // Create panels and components here...

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

        frame.setLayout(new BorderLayout());
        // Add components to panels and panels to the frame...

        frame.setVisible(true);
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
