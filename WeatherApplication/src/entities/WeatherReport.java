package entities;

/**
 * This class represents weather report to be displayed on screen.
 * @author Urvi
 *
 */
public class WeatherReport {	
	
	protected final String temperature;
	protected final String humidity;
	protected final String windSpeed;
	protected final String date;
	
	
	/**
	 * 
	 * @param temperature
	 * @param humidity
	 * @param windSpeed
	 * @param date
	 */
	public WeatherReport(String temperature, String humidity,
			String windSpeed,String date) {
		super();
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.date = date; 
	}
	
	/**
	 * 
	 * @return temperature
	 */
	public String getTemperature() {
		return temperature;
	}
	
	/**
	 * 
	 * @return humidity
	 */
	public String getHumidity() {
		return humidity;
	}
	
	/**
	 * 
	 * @return windSpeed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}
	
	/**
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
}
