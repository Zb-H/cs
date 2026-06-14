package JUnitTestScript;
/*
    
    A program that inputs temperature in degrees Celsius
    and outputs the temperature in equivalent degrees Fahrenheit. 
    
 */

import java.util.*;

class TemperatureConverter {

	public static void main(String[] args) {
		Scanner         scanner;
		double          celsius, fahrenheit;
		Temperature     converter;
		
		scanner = new Scanner( System.in );
		converter = new Temperature();
		
		//Get input
		System.out.print("Enter temperature in celsius (e.g. 32.5): ");
		celsius = scanner.nextFloat();
		
		converter.setCelsius(celsius);
		
		//Convert to fahrenheit
		fahrenheit = converter.toFahrenheit();
		
		//Display the result
		System.out.println();
		System.out.println("Input:  " + celsius + "C is equivalent to");
		System.out.println("Output: " + fahrenheit + "F");
	}
}
