package JUnitTestScript;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The TemperatureTest class contains JUnit test cases for the Temperature class.
 *
 * This class tests whether the toCelsius and toFahrenheit methods correctly
 * convert temperatures between Fahrenheit and Celsius.
 *
 * @author Zhaobi Huang
 */
public class TemperatureTest{

    @Test
    public void testToCelsius(){
        Temperature temp = new Temperature();

        assertEquals(0.0, temp.toCelsius(32.0), 0.001);
        assertEquals(100, temp.toCelsius(212.0), 0.001);
        assertEquals(-50, temp.toCelsius(-58), 0.001);
    }

    @Test
    public void testToFahrenheit(){
        Temperature temp = new Temperature();

        assertEquals(87.98, temp.toFahrenheit(31.1), 0.001);
        assertEquals(35.96, temp.toFahrenheit(2.2), 0.001);
        assertEquals(32, temp.toFahrenheit(0), 0.001);
    }
}


