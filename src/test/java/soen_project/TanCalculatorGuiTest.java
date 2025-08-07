package soen_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TanCalculatorGuiTest {

    // Instance of the main class
    TanCalculatorGui calc = new TanCalculatorGui();

    @Test
    public void testFactorial() {
        assertEquals(1, calc.factorial(0));
        assertEquals(1, calc.factorial(1));
        assertEquals(120, calc.factorial(5));
    }

    @Test
    public void testCalculateSin() {
        double result = calc.calculateSin(0);
        assertEquals(0.0, result, 1e-6);

        result = calc.calculateSin(Math.PI / 2); // Should be ~1
        assertEquals(1.0, result, 0.01);
    }

    @Test
    public void testCalculateCos() {
        double result = calc.calculateCos(0);
        assertEquals(1.0, result, 1e-6);

        result = calc.calculateCos(Math.PI); // Should be ~ -1
        assertEquals(-1.0, result, 0.01);
    }
}
