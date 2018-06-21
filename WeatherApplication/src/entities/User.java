package entities;

/**
 * This class represents user of application
 * @author Urvi
 *
 */
public class User {
	
	/*   user name  */
	private String uname;
	private String password;	
	private String firstName;
	private String lastName;
	private String email;
	
	/**
	 * 
	 * @param uname
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public User(String uname, String password, String firstName,
			String lastName, String email) 
	{
		super();
		this.uname = uname;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	/**
	 * 
	 * @return uname
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * 
	 * @param uname
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
