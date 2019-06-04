/**
 * @author Brent Dalling
 * 
 * @date June 4th, 2019
 * 
 */


import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;



public class Request {
	
	protected String type;
	protected String parameters;
	protected String url;
	private static HttpURLConnection con;
	protected String accessToken;
	protected String refreshToken;
	protected String tokenType;

	public Request(String url) {
		
		// Set Data
		this.url = url;
		
	}
	
	/**
	 * <h1>register()</h1>
	 * <p>Registers The Java Client With The External API.</p>
	 * 
	 * @param uri (String: String To OAuth Token)
	 * @param parameters (String: Reference Docs.timelydevs.com)
	 * @return (JSON String)
	 */
	public String register(String uri, String parameters) {
		byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
		
		try {

			URL myurl = new URL(this.url + uri);
			con = (HttpURLConnection) myurl.openConnection();
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		    con.setRequestMethod("POST");
			con.setDoOutput(true);
			
			
			try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
				out.write(postData);
			}
			
			StringBuilder content;

			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()))) {

				String line;
				content = new StringBuilder();

				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}
			
			con.disconnect();

			return content.toString();

			
		} catch (Exception e) {
			return e.toString();
		}
	}
	

	/**
	 * <h1>post()</h1>
	 * <p>Performs A "POST" Request To The API.</p>
	 * @param uri - (String: Path To API Resource)
	 * @param parameters - (String: Parameters Supplied To API)
	 * @return (JSON String)
	 */
	public String post(String uri, String parameters) {
			byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
			
			try {

				URL myurl = new URL(this.url + uri);
				con = (HttpURLConnection) myurl.openConnection();
				con.setRequestProperty("User-Agent", "Java client");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", "Bearer " + this.getAccessToken());

			    con.setRequestMethod("POST");
				con.setDoOutput(true);
				
				
				try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
					out.write(postData);
				}
				
				StringBuilder content;

				try (BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()))) {

					String line;
					content = new StringBuilder();

					while ((line = in.readLine()) != null) {
						content.append(line);
						content.append(System.lineSeparator());
					}
				}
				
				con.disconnect();

				return content.toString();

				
			} catch (Exception e) {
				return e.toString();
			}
		}
		
	/**
	 * <h1>get()</h1>
	 * <p>Performs A "GET" Request To The API.</p>
	 * @param uri - (String: Path To API Resource)
	 * @param parameters - (String: Parameters To API Resource)
	 * @return (JSON String)
	 */
	public String get(String uri, String parameters) {
		try {
			
					

					URL myurl = new URL(url + uri);
					con = (HttpURLConnection) myurl.openConnection();
					
					con.setRequestProperty("User-Agent", "Java client");
					con.setRequestProperty("Content-Type", "application/json");
					con.setRequestProperty("Authorization", "Bearer " + this.getAccessToken());

					con.setRequestMethod("GET");

					StringBuilder content;

					try (BufferedReader in = new BufferedReader(
							new InputStreamReader(con.getInputStream()))) {

						String line;
						content = new StringBuilder();

						while ((line = in.readLine()) != null) {
							content.append(line);
							content.append(System.lineSeparator());
						}
					}
					
					con.disconnect();

					return content.toString();

		} catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * <h1>setAccessToken()</h1>
	 * <p> Sets The Requests Object Access Token To API </p>
	 * @param token - (String: Reference Docs.timelydevs.com)
	 */
	public void setAccessToken(String token) {
		this.accessToken = token;
	}
	
	/**
	 * <h1>setRefreshToken()</h1>
	 * <p> Sets The Requests Object Refresh Token To API </p>
	 * @param token - (String: Reference Docs.timelydevs.com)
	 */
	public void setRefreshToken(String token) {
		this.refreshToken = token;
	}
	
	/**
	 * <h1>setTokenType()</h1>
	 * <p> Sets The Requests Object Token Type To API </p>
	 * @param token -  (String: Reference Docs.timelydevs.com)
	 */
	public void setTokenType(String type) {
		this.tokenType = type;
	}
	
	/**
	 * <h1>getAccessToken()</h1>
	 * <p> Retrieves The Access Token From The Request Object </p>
	 * @return String (AccessToken)
	 */
	public String getAccessToken() {
		return this.accessToken;
	}
	
	/**
	 * <h1>getRefreshToken()</h1>
	 * <p> Retrieves The Refresh Token From The Request Object </p>
	 * @return String (AccessToken)
	 */
	public String getRefreshToken() {
		return this.refreshToken;
	}
	
	/**
	 * <h1>getTokenType()</h1>
	 * <p> Retrieves The Token Type From The Request Object </p>
	 * @return String (AccessToken)
	 */
	public String getTokenType() {
		return this.tokenType;
	}
}