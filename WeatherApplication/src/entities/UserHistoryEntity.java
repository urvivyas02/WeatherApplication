package entities;

/**
 * Class represents user's serach history
 * @author Urvi
 *
 */
public class UserHistoryEntity {
	
	/* city searched. for example,  Toronto,ca*/
	private String city;
	
	/* Date and time of search */
	private String timestamp;
	
	/**
	 * 
	 * @param city
	 * @param timestamp
	 */
	public UserHistoryEntity(String city, String timestamp) 
	{
		super();
		this.city = city;
		this.timestamp = timestamp;
	}
	/**
	 * 
	 * @return email
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 
	 * @return timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
