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

			return content.toString();

			
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public String post(String uri, String parameters) {
		
		
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

			return content.toString();

			
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public String get(String uri, String parameters) {
		try {

					URL myurl = new URL(url);
					con = (HttpURLConnection) myurl.openConnection();

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

					return content.toString();

		} catch (Exception e) {
			return e.toString();
		}
	}
	
	
	public void setAccessToken(String token) {
		this.accessToken = token;
	}
	
	public void setRefreshToken(String token) {
		this.refreshToken = token;
	}
	
	public void setTokenType(String type) {
		this.tokenType = type;
	}
	
	public String getAccessToken() {
		return this.accessToken;
	}
	
	public String getRefreshToken() {
		return this.refreshToken;
	}
	
	public String getTokenType() {
		return this.tokenType;
	}
}