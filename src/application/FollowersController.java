package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FollowersController {

	@FXML
	public Label userName;
	
	//
	// Scene Controller
	// 
	private Stage stage;
	private Scene scene;
	private Parent root;
	 
	public void switchPrevScene(ActionEvent event) throws IOException{
		
    	if (SceneData.PrevScene == null) {
			root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = SceneData.PrevScene;
			stage.setScene(scene);
			stage.show();
		}
		
	}
	
	public void switchToFollowing(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("following.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
