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
 * <p>This application provides a GUI for user input, validates the input,
 * and computes tan(x) using custom implementations of sin(x) and cos(x).
 *
 * @author Sushmitha
 * @version 1.0.0
 */
public class TanCalculatorGui extends JFrame {

  /** Semantic version of the TanCalculatorGui application. */
  public static final String VERSION = "1.0.0";

  private final JTextField inputField;
  private final JLabel resultLabel;

  /**
   * Initializes the TanCalculator GUI components and layout.
   * Sets up user input, buttons, labels, and their respective event handlers.
   */
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

    computeButton.addActionListener(_ -> computeTan());
    clearButton.addActionListener(_ -> {
      inputField.setText("");
      resultLabel.setText(" ");
    });
    exitButton.addActionListener(_ -> System.exit(0));

    setVisible(true);
  }

  /**
   * Computes the tangent of the input value using Taylor series approximations
   * for sine and cosine. Displays the result or an error message.
   */
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

  /**
   * Computes the sine of a given angle using the Taylor series expansion.
   *
   * @param x the input angle in radians
   * @return the approximated value of sin(x)
   */
  private double calculateSin(double x) {
    double sum = 0;
    for (int n = 0; n < 10; n++) {
      sum += Math.pow(-1, n) * Math.pow(x, 2.0 * n + 1) / factorial((int) (2.0 * n + 1));
    }
    return sum;
  }

  /**
   * Computes the cosine of a given angle using the Taylor series expansion.
   *
   * @param x the input angle in radians
   * @return the approximated value of cos(x)
   */
  private double calculateCos(double x) {
    double sum = 0;
    for (int n = 0; n < 10; n++) {
      sum += Math.pow(-1, n) * Math.pow(x, 2.0 * n) / factorial((int) (2.0 * n));
    }
    return sum;
  }

  /**
   * Calculates the factorial of a non-negative integer.
   *
   * @param n the integer value
   * @return factorial of the given number
   */
  private long factorial(int n) {
    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * Entry point of the application. Launches the TanCalculator GUI.
   *
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(TanCalculatorGui::new);
  }
}
