/**
*
* @author Brent Dalling
*
* @date June 4th, 2019
* 
* Facilitates logging into an external API and managing user information.
*
*/


import java.net.*;
import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.nio.charset.StandardCharsets;

public class APILogin extends Application {

 static TextField username = new TextField();
 static TextField password = new TextField();
 private static User user = new User();
 public static Scene scene;


 @Override
 public void start(Stage primaryStage) {

	BorderPane pane = new BorderPane();
	VBox box = new VBox();

	ImageView image = new ImageView(new Image("http://timelydevs.com/images/timely/logo.png"));
	Button button = new Button("Login");

	image.setFitWidth(250.0);
	image.setFitHeight(70);
	
	this.username.setPromptText("Username");
	this.password.setPromptText("Password");

	button.setOnAction(e -> {
		
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

		 if (request.getAccessToken() != null)
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

	// Set Pane / Menu's / Buttons
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	BorderPane pane = new BorderPane();
	HBox horizontalMenu = new HBox();
	VBox verticalMenu = new VBox();
	Button userButton = new Button("User Details");
	Button userSave = new Button("Save Changes");
	Button userCreationButton = new Button("User Creation");

	// Set Element Id's For CSS Use
	userButton.setId("nav");
	userCreationButton.setId("nav");
	userSave.setId("submit");

	// Get Response From The API
	JSON response = new JSON(request.get("/me", null));

	// Set Response Values To User Object Attributes
	user.setEmail(response.get("email"));
	user.setCompanyName(response.get("company_name"));
	user.setID(response.get("id"));

	// Set Email TextField
	TextField email = new TextField();
	email.setPromptText(user.getEmail());

	// Set Company Name TextField With Ternary
	TextField companyName = new TextField();
	companyName.setPromptText(
	 user.getCompanyName() != null ? user.getCompanyName() : "Enter Company Name (Optional)"
	);

	// Set UserSave Action
	userSave.setOnAction((e) -> {

	 // Set New Values To User Object
	 if (companyName.getText() != null)
		user.setCompanyName(companyName.getText());
	 else if (email.getText() != null)
		user.setEmail(email.getText());

	 // Send Update To API
	 JSON setResponse = new JSON(request.post("/users/update",
		"{\"id\" : \"" + user.getID() +
		"\", \"company_name\" : \"" + user.getCompanyName() +
		"\", \"email\" : \"" + user.getEmail() +
		"\"}"
	 ));

	 if (setResponse.get("Message") != null) {

		// Clear And Set Email
		email.setText(null);
		email.setPromptText(user.getEmail());

		// Clear and Set Company Name
		companyName.setText(null);
		companyName.setPromptText(user.getEmail());
	 }

	 System.out.println(setResponse.get("message"));
	 System.out.println(setResponse.get("error"));
	});


	userCreationButton.setOnAction((e) -> {

		 StackPane userCreationPane = new StackPane();

		 TextField firstName = new TextField();
		 firstName.setPromptText("John...");

		 TextField lastName = new TextField();
		 lastName.setPromptText("Smith...");

		 //TextField email = new TextField();
		 email.setPromptText("john.smith@example.com");

		 TextField position = new TextField();
		 position.setPromptText("Developer");

		 TextField cellPhone = new TextField();
		 cellPhone.setPromptText("xxx xxx xxxx");

		 TextField v2fa = new TextField();
		 v2fa.setPromptText("text/call/app");

		 TextField password = new TextField();
		 password.setPromptText("password");

		 Button goBack = new Button("Go Back");
		 goBack.setOnAction((action) -> {
			changeScene(primaryStage, request);
		 });

		 Button submitUser = new Button("Create User");
		 submitUser.setOnAction((ea) -> {

			System.out.println(firstName.getText());
			System.out.println(lastName.getText());
			System.out.println(email.getText());
			System.out.println(position.getText());
			System.out.println(password.getText());
			System.out.println(cellPhone.getText());
			System.out.println(v2fa.getText());

			JSON setResponse = new JSON(request.post("/users/create",
				 "{\"name\" : \"" + firstName.getText() + lastName.getText() +
				 "\", \"first_name\" : \"" + firstName.getText() +
				 "\", \"last_name\" : \"" + lastName.getText() +
				 "\", \"email\" : \"" + email.getText() +
				 "\", \"position\" : \"" + position.getText() +
				 "\", \"cell_phone\" : \"" + cellPhone.getText() +
				 "\", \"password\" : \"" + password.getText() +
				 "\", \"2fa\" : \"" + v2fa.getText() +
				 "\"}"
			));

			if (setResponse.get("message") != null)
			 	changeScene(primaryStage, request);
		 });

		 userCreationPane.getChildren().addAll(new VBox(goBack, firstName, lastName, email, password, position, cellPhone, v2fa, submitUser));
		 userCreationPane.setMaxWidth((screen.getWidth() / 2) / 2);
		 pane.setCenter(userCreationPane);
		 scene = new Scene(userCreationPane, screen.getWidth() / 2, screen.getHeight() / 2);
		 scene.getStylesheets().add("app.css");
		 primaryStage.setScene(scene);

	});


	// throw the mess together
	verticalMenu.getChildren().addAll(email, companyName, userSave);
	horizontalMenu.getChildren().addAll(new VBox(userButton, userCreationButton), verticalMenu);

	pane.setCenter(horizontalMenu);
	scene = new Scene(pane, screen.getWidth() / 2, screen.getHeight() / 2);
	scene.getStylesheets().add("app.css");
	primaryStage.setScene(scene);
 }


 public static void main(String[] args) {
	launch(args);
 }
}