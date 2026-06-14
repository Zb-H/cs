package JUnitTestScript;

/*
   Chapter 4
 
  This class can be used to convert from Celsius to Fahrenheit and vice versa.
 */

public class Temperature {

	//----------------------------------
	//    Data Members
	//----------------------------------

	private double fahrenheit = 0;

	//-------------------------------------------------
	//      Public Methods:
	// 
	//          double toFahrenheit()
	//          double toCelsius()
	//
	//          void setFahrenheit(double)
	//          void setCelsius(double)
	//------------------------------------------------
	
	public double toFahrenheit() {
		return fahrenheit;
	}
	
	public double toFahrenheit(double tempC) 
	{	
		return (((tempC / 5) * 9) + 32);
	}
	
	
	public double toCelsius() 
	{
		return ( 5.0 / 9.0 ) * ( fahrenheit - 32 );
	}
	
	public double toCelsius(double tempF) 
	{
		return (((tempF - 32) * 5) / 9 );
	}
	
	public void setFahrenheit(double degrees) 
	{
		fahrenheit = degrees;
	}
	
	public void setCelsius(double degrees) 
	{
		fahrenheit = 1.8 * degrees + 32; 
	}

}
