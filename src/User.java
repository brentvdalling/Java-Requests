/**
 * 
 * @author Brent Dalling
 * 
 * @date June 4th, 2019
 *
 */

public class User {
	
	// User Object Attributes
	private  static String id;
	private  static String email;
	private  static String email_verified_at;
	private  static String company_name;
	private  static String company_phone;
	private  static String company_address;
	private  static String first_name;
	private  static String last_name;
	private  static String middle_name;
	private  static String position;
	private  static String cell_phone;
	private  static String work_phone;
	private  static String created_at;
	private  static String updated_at;
	
	
	
	
	
	/**
	 * <h1>setID()</h1>
	 * <p>Sets the User ID Of The User Object</p>
	 * @param id
	 */
	public void setID(String id) { this.id = id; }
	
	/**
	 * <h1>getID()</h1>
	 * <p>Gets the User ID Of The User Object</p>
	 */
	public static String getID() { return id; }
	
	/**
	 * <h1>setEmail()</h1>
	 * <p>Sets the Email Address Of The User Object</p>
	 * @param email
	 */
	public void setEmail(String email) { this.email = email; };
	
	/**
	 * <h1>getEmail()</h1>
	 * <p>Get's The Email Of The User Object</p>
	 */
	public static String getEmail() { return email; };
	
	/**
	 * <h1>setEmailVerifiedAt()</h1>
	 * <p>Sets the Email Verification Date Of The User Object</p>
	 * @param email_verified_at
	 */
	public void setEmailVerifiedAt(String email_verified_at) { this.email_verified_at = email_verified_at; }
	
	/**
	 * <h1>getEmailVerifiedAt()</h1>
	 * <p>Get's The Date The Email Was Verified From The User Object</p>
	 */
	public String getEmailVerifiedAt() { return email_verified_at; }
	
	/**
	 * <h1>setCompanyName()</h1>
	 * <p>Sets the Company Name In The User Object</p>
	 * @param id
	 */
	public void setCompanyName(String company_name) { this.company_name = company_name; }
	
	/**
	 * <h1>getCompanyName()</h1>
	 * <p>Gets the Company Name From The User Object</p>
	 */
	public static String getCompanyName() { return company_name; }
}