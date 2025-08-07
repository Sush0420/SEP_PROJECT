package soen_project;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * A graphical calculator for computing the tangent of an angle (in radians)
 * using Taylor series approximation.
 *
 * <p>This application provides a GUI with input validation, sin(x)/cos(x) computation,
 * and dynamic result rendering.
 */
public class TanCalculatorGui extends JFrame {

  private final JTextField inputField;
  private final JLabel resultLabel;

  public TanCalculatorGui() {
    setTitle("tan(x) Calculator");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(400, 200);
    setLocationRelativeTo(null);
    setLayout(new GridLayout(4, 1));

    inputField = new JTextField();
    inputField.setToolTipText("Enter x in radians");

    JButton computeButton = new JButton("Compute tan(x)");
    JButton clearButton = new JButton("Clear");
    final JButton exitButton = new JButton("Exit");

    resultLabel = new JLabel(" ", SwingConstants.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(computeButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(exitButton);

    add(new JLabel("Enter x (in radians):", SwingConstants.CENTER));
    add(inputField);
    add(buttonPanel);
    add(resultLabel);

    computeButton.addActionListener(_ -> computeTan()); // 🔧 FIX 2: Use `_` if variable not used
    clearButton.addActionListener(_ -> {
      inputField.setText("");
      resultLabel.setText(" ");
    });

    exitButton.addActionListener(_ -> System.exit(0));
    setVisible(true);
  }

  private void computeTan() {
    try {
      double x = Double.parseDouble(inputField.getText());
      double sinX = calculateSin(x);
      double cosX = calculateCos(x);
      if (cosX == 0) {
        resultLabel.setText("Undefined (cos(x) is 0)");
      } else {
        resultLabel.setText("tan(x) = " + sinX / cosX);
      }
    } catch (NumberFormatException _) {
      resultLabel.setText("Invalid input. Please enter a valid number.");
    }
  }

  private double calculateSin(double x) {
    double sum = 0;
    for (int n = 0; n < 10; n++) {
      sum += Math.pow(-1, n) * Math.pow(x, 2.0 * n + 1) / factorial((int) (2.0 * n + 1)); // 🔧 FIX 3
    }
    return sum;
  }

  private double calculateCos(double x) {
    double sum = 0;
    for (int n = 0; n < 10; n++) {
      sum += Math.pow(-1, n) * Math.pow(x, 2.0 * n) / factorial((int) (2.0 * n)); // 🔧 FIX 4
    }
    return sum;
  }

  private long factorial(int n) {
    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(TanCalculatorGui::new);
  }
}
