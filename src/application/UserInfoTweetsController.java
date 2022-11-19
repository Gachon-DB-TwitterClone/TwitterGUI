package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserInfoTweetsController {

	
	public Button btnFollow;
	
	public void follow(ActionEvent event) {
		String fol = btnFollow.getText();
		
		//if user clicked follow btn, change to following
		if(fol.equalsIgnoreCase("follow")) {
		
			btnFollow.setText("Following");
			btnFollow.setStyle("-fx-background-color: white; -fx-background-radius: 30px; -fx-border-color: #C0C0C0; -fx-border-radius: 30px; -fx-text-fill: black");
			
		}
		// else if user clicked following btn to unfollow, change to follow
		
		if(fol.equalsIgnoreCase("following")) {
			
			btnFollow.setText("Follow");
			btnFollow.setStyle("-fx-background-color: black; -fx-background-radius: 30px; -fx-border-color: black; -fx-border-radius: 30px; -fx-text-fill: white");
		}
		
		
	}
	
	
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
	
	
	public void switchToFollowing(MouseEvent event) throws IOException{
		
		SceneData.PrevScene = ((Node)event.getSource()).getScene();
		
		root = FXMLLoader.load(getClass().getResource("following.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToFollowers(MouseEvent event) throws IOException{
		
		SceneData.PrevScene = ((Node)event.getSource()).getScene();
		
		root = FXMLLoader.load(getClass().getResource("followers.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTweetsNReplies(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("UserInfo-tnr.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToLikes(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("UserInfo-likes.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// switch to writh_post page
	public void switchToWritepost(ActionEvent event) throws IOException{
		
		SceneData.PrevScene = ((Node)event.getSource()).getScene();
		
		root = FXMLLoader.load(getClass().getResource("WritePost.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// switch to main page
	public void switchToMain(ActionEvent event) throws IOException{
		
		SceneData.PrevScene = ((Node)event.getSource()).getScene();

		root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
		
		
		
	// switch to search page
	public void switchToSearch(ActionEvent event) throws IOException{
		
		SceneData.PrevScene = ((Node)event.getSource()).getScene();
		
		root = FXMLLoader.load(getClass().getResource("search.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	
}
