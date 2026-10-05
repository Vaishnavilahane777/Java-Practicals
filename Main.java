import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private final JTextField nameField;
    private final JTextField rollField;
    private final JTextField marksField;
    private final JTextArea outputArea;

    public Main() {
        super("Student Registration");
        setLayout(new BorderLayout(12, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        JLabel rollLabel = new JLabel("Roll No:");
        JLabel marksLabel = new JLabel("Marks:");

        nameField = new JTextField(20);
        rollField = new JTextField(20);
        marksField = new JTextField(20);

        addField(formPanel, gbc, 0, nameLabel, nameField);
        addField(formPanel, gbc, 1, rollLabel, rollField);
        addField(formPanel, gbc, 2, marksLabel, marksField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> registerStudent());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearForm());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Result"));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, JLabel label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    private void registerStudent() {
        String name = nameField.getText().trim();
        String rollNo = rollField.getText().trim();
        String marksText = marksField.getText().trim();

        if (name.isEmpty() || rollNo.isEmpty() || marksText.isEmpty()) {
            outputArea.setText("Please fill in all fields.");
            return;
        }

        double marks;
        try {
            marks = Double.parseDouble(marksText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Marks must be a valid number.");
            return;
        }

        if (marks < 0 || marks > 100) {
            outputArea.setText("Marks must be between 0 and 100.");
            return;
        }

        String grade = calculateGrade(marks);

        outputArea.setText(
                "Student Details\n" +
                "---------------------------\n" +
                "Name: " + name + "\n" +
                "Roll No: " + rollNo + "\n" +
                "Marks: " + marks + "\n" +
                "Grade: " + grade
        );
    }

    private void clearForm() {
        nameField.setText("");
        rollField.setText("");
        marksField.setText("");
        outputArea.setText("");
        nameField.requestFocus();
    }

    private String calculateGrade(double marks) {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
