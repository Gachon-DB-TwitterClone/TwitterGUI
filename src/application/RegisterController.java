package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController {

	@FXML
	private TextField name;
	@FXML
	private TextField u_id;
	@FXML
	private TextField u_pw;
	@FXML
	private TextField nick;
	@FXML
	private TextField email;
	
	@FXML
	private AnchorPane errorMessage;
	
	
	@FXML
	private void signUp(ActionEvent event) throws IOException{


		Model model = new Model();


		if(model.signUp(u_id.getText(), u_pw.getText(), name.getText(), nick.getText(), email.getText())){
			AlertBox.display("Congratulation!", "Register Success!");


			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} else {
			errorMessage.setVisible(true);
		}



	}
	
	
	
	
	
	
	
	
	//
	// Scene Controller
	// 
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// switch to log in page
	public void switchToLogin(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
