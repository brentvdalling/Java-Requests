public class User {
	
	// User Object Attributes
	private  int id;
	private  String email;
	private  String email_verified_at;
	private  String company_name;
	private  String company_phone;
	private  String company_address;
	private  String first_name;
	private  String last_name;
	private  String middle_name;
	private  String position;
	private  String cell_phone;
	private  String work_phone;
	private  String created_at;
	private  String updated_at;
	
	
	
	
	
	
	public void setID(int id) { this.id = id; }
	public int getID() { return id; }
	
	public void setEmail(String email) { this.email = email; };
	public static String getEmail() { return this.email; };
	
	public void setEmailVerifiedAt(String email_verified_at) { this.email_verified_at = email_verified_at; }
	public String getEmailVerifiedAt() { return this.email_verified_at; }
}