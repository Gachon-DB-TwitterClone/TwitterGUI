package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class WritePostController {
	@FXML
	private TextArea myTArea;
	
	public void tweet(ActionEvent event) {
		Model model = new Model();
		String content = myTArea.getText();
		if(content.length() < 3) {
			AlertBox.display("Alert", "Write Something!");
		}
		else {
			model.tweet(content);
			try {
				root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	//
	// Scene Controller
	// 
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    public void switchToMain(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	}
}
