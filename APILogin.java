import java.net.*;
import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import java.awt.*;
import java.nio.charset.StandardCharsets;

public class APILogin extends Application {
	
	static TextField username = new TextField();
	static TextField password = new TextField();
	
	private User user = new User();
	
	public static Scene scene;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		VBox box = new VBox();
		
		
		ImageView  	image   	= new ImageView(new Image("http://timelydevs.com/images/timely/logo.png"));
		Button 		button 		= new Button("Login");
		
		image.setFitWidth(250.0);
		image.setFitHeight(70);
		this.username.setPromptText("Username");
		this.password.setPromptText("Password");
		
		button.setOnAction(e-> {
			attemptLogin(username.getText(), password.getText(), primaryStage);
		});
		
		box.getChildren().addAll(image, username, password, button);
		box.setMaxWidth(250.0);
		box.setSpacing(5);
		
		pane.setCenter(box);
		
		
		
		
		
		scene = new Scene(pane, 300, 250);
		scene.getStylesheets().add("app.css");
		primaryStage.setTitle("API Management"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	
	public static void attemptLogin(String user, String pass, Stage primaryStage) {
		
		//changeScene(1);
				
		Request request = new Request("http://ins.api.timelydevs.com");
		
		JSON response = new JSON(request.register("/oauth/token", 
									"client_id=1" +
									"&client_secret=9mHRf6ZDd5iUmOSGbHVHnOTTq8Q4y99jnh8RNM9O" +
									"&grant_type=password" +
									"&username=" + user +
									"&password=" + pass
								));
		
		try {
			
			// Set Request Class Information
			request.setAccessToken(response.get("access_token"));
			request.setRefreshToken(response.get("access_token"));
			request.setTokenType(response.get("token_type"));
			
			if(request.getAccessToken() != null)
				changeScene(primaryStage, request);
			else {
				showFail();
			} 
				
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void showFail() {
		username.setStyle("-fx-text-inner-color: red;");
		password.setStyle("-fx-text-inner-color: red;");
	}
	
	public static void changeScene(Stage primaryStage, Request request) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		BorderPane pane = new BorderPane();
		HBox horizontalMenu = new HBox();
		VBox verticalMenu = new VBox();
		
		Button user = new Button("User Details");
		Button blog = new Button("Blog Management");
		
		user.setId("nav");
		blog.setId("nav");
		
		horizontalMenu.getChildren().addAll(user, blog);
		
		pane.setCenter(horizontalMenu);
		
		System.out.println(request.get("/me", null));
		
		JSON response = new JSON(request.get("/me", null));
		
		user.setEmail(response.get("email"));
		
		System.out.println(user.getEmail());
		
		
		TextField email = new TextField();
		
		
		verticalMenu.getChildren().addAll(email);
		
		pane.getChildren().addAll(email);
		
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		scene.getStylesheets().add("app.css");
		
		primaryStage.setScene(scene);
	}

    	
	public static void main(String[] args) { 
		launch(args);
	}
}