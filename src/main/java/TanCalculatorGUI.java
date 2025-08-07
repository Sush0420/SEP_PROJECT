 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TanCalculatorGUI extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public TanCalculatorGUI() {
        setTitle("tan(x) Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        inputField = new JTextField();
        inputField.setToolTipText("Enter x in radians");
        JButton computeButton = new JButton("Compute tan(x)");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        resultLabel = new JLabel(" ", SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(computeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        add(new JLabel("Enter x (in radians):", SwingConstants.CENTER));
        add(inputField);
        add(buttonPanel);
        add(resultLabel);

        computeButton.addActionListener(e -> computeTan());
        clearButton.addActionListener(e -> {
            inputField.setText("");
            resultLabel.setText(" ");
        });
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void computeTan() {
        try {
            double x = Double.parseDouble(inputField.getText());

            double sin = calculateSin(x);
            double cos = calculateCos(x);

            if (Math.abs(cos) < 1e-6) {
                resultLabel.setForeground(Color.RED);
                resultLabel.setText("tan(x) is undefined at x = " + String.format("%.6f", x));
                return;
            }

            double tan = sin / cos;
            resultLabel.setForeground(Color.BLACK);
            resultLabel.setText("tan(" + String.format("%.6f", x) + ") = " + String.format("%.6f", tan));

        } catch (NumberFormatException ex) {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    // Taylor series for sin(x)
    private double calculateSin(double x) {
        double sum = 0;
        for (int n = 0; n < 10; n++) {
            sum += Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / factorial(2 * n + 1);
        }
        return sum;
    }

    // Taylor series for cos(x)
    private double calculateCos(double x) {
        double sum = 0;
        for (int n = 0; n < 10; n++) {
            sum += Math.pow(-1, n) * Math.pow(x, 2 * n) / factorial(2 * n);
        }
        return sum;
    }

    private double factorial(int n) {
        double fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TanCalculatorGUI::new);
    }
}
