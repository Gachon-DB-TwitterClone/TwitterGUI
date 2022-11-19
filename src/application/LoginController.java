package application;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
	
	//
	// Log in controller
	//
	
	private Model model = new Model();
	
	// user input variable
	@FXML
	private TextField uid;
	@FXML
	private PasswordField upwd;
	@FXML
	private Text loginError;
	
	
	@FXML
	private void login(ActionEvent event) {
		
		if(model.login(uid.getText(), upwd.getText()) == true) {
			AlertBox.display("Log In Success", "Welcome, " + uid.getText() + "!");	
			// switch to main page
			try {
				
				String id = uid.getText();
				LocalUser.id = id;
				model.setUser(id);

				root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
				
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch(IOException e) {
				e.printStackTrace();
			}		
		}
		else {
			loginError.setVisible(true);
		}
		
	}


	//
	// Scene Controller
	// 
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// switch to sign up
	public void switchToSignup(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("register.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// switch to non-loggedin main
	
	public void switchToNonLoginMain(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("Guest_MainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
}
