package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyPageTweetsNRepliesController implements Initializable {
	@FXML
	public Label u_id;
	public Label u_name;
	public Label following;
	public Label follower;

	//
	// Scene Controller
	// 
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	// switch to main page
	public void switchToMain(ActionEvent event) throws IOException{

		SceneData.MyPage_tweetsnreplies = (Scene)((Node)event.getSource()).getScene();

		root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// switch to search page
	public void switchToSearch(ActionEvent event) throws IOException{
		
		SceneData.MyPage_tweetsnreplies = ((Node)event.getSource()).getScene();
		
		root = FXMLLoader.load(getClass().getResource("search.fxml"));
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
	
	
	
	
	
	
	// switch to MY page - tweets
	public void switchToMyTweets(ActionEvent event) throws IOException{
		SceneData.MyPage_tweetsnreplies = ((Node)event.getSource()).getScene();
		
		if (SceneData.MyPage_tweets == null) {
			root = FXMLLoader.load(getClass().getResource("������-tweets.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = SceneData.MyPage_tweets;
			stage.setScene(scene);
			stage.show();
		}
	}
		
	// switch to MY page - likes
	public void switchToMyLikes(ActionEvent event) throws IOException{
		SceneData.MyPage_tweetsnreplies = ((Node)event.getSource()).getScene();
		
		if (SceneData.MyPage_likes == null) {
			root = FXMLLoader.load(getClass().getResource("myinfo-likes.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = SceneData.MyPage_likes;
			stage.setScene(scene);
			stage.show();
		}
	}

	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Model model = new Model();
		u_id.setText(LocalUser.id);
		u_name.setText(LocalUser.name);
		following.setText(String.valueOf((model.getFollowingById(LocalUser.seeing_userid).size())));
		follower.setText(String.valueOf((model.getFollowerById(LocalUser.seeing_userid).size())));
		
		// set Tweets & replies
		// fill Tweets & replies
		
		
		
		
		
	}
}
