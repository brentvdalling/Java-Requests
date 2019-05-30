import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;
import java.io.*;

class JSON {
	
	protected String originalString;
	protected JSONObject parsed;
	
	public JSON(String data) {
		
		// Set original string
		this.originalString = data;
	}
	
	public Object parse() throws ParseException {
		try {
			this.parsed = (JSONObject) new JSONParser().parse(this.originalString);
			
			System.out.println("Access Token ----- " + this.parsed.get("access_token") + "--------- End Token");
			return this.parsed;
		} catch (ParseException e) {
			System.out.println(e);
			return e;
		}
	}
	
	public String get(String key) {
			
			try {
				return (String)((JSONObject) new JSONParser().parse(this.originalString)).get(key);
			} catch (ParseException e) {
				return null;
			}
	}
	
	
	
}