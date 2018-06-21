package entities;

/**
 * This class represents entity which contains parameters needed to define current weather conditions.
 * @author Urvi
 *
 */
public class CurrentWeatherReport extends WeatherReport{
	
	protected final String cityName;
	protected final String weatherConditionDesc;
	protected final String iconID;
	
	
	/**
	 * 
	 * @param cityName
	 * @param date
	 * @param temperature
	 * @param weatherConditionDesc
	 * @param iconID
	 * @param humidity
	 * @param windSpeed
	 */
	public CurrentWeatherReport(String cityName, String date,
			String temperature, String weatherConditionDesc, String iconID,
			String humidity, String windSpeed) {
		super(temperature, humidity, windSpeed,date);
		this.cityName = cityName;		
		this.weatherConditionDesc = weatherConditionDesc;
		this.iconID = iconID;	
	}

	/**
	 * 
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 
	 * @return weatherConditionDesc
	 */
	public String getWeatherConditionDesc() {
		return weatherConditionDesc;
	}
	
	/**
	 * 
	 * @return iconID
	 */
	public String getIconID() {
		return iconID;
	}

}
