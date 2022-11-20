package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyPageTweetsController implements Initializable {
	@FXML
	public Label u_id;
	public Label u_name;
	public Label following;
	public Label follower;
	
	public VBox vbox;

	public VBox PostsContainer;
	
//
//	public void addPost(ActionEvent event) {
//		Node post;
//		post =
//		vbox.getChildren().add(post);
//	}
	
	
	//
	// Scene Controller
	// 

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// switch to main page
	public void switchToMain(ActionEvent event) throws IOException{
		
		SceneData.PrevScene.add(((Node)event.getSource()).getScene());

		root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	// switch to search page
	public void switchToSearch(ActionEvent event) throws IOException{
		
		SceneData.PrevScene.add(((Node)event.getSource()).getScene());
		
		root = FXMLLoader.load(getClass().getResource("search.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// switch to writh_post page
	public void switchToWritepost(ActionEvent event) throws IOException{
		
		SceneData.PrevScene.add(((Node)event.getSource()).getScene());
		
		root = FXMLLoader.load(getClass().getResource("WritePost.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public void switchToFollowing(MouseEvent event) throws IOException{
		
		SceneData.PrevScene.add(((Node)event.getSource()).getScene());
		
		root = FXMLLoader.load(getClass().getResource("following.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToFollowers(MouseEvent event) throws IOException{
		
		SceneData.PrevScene.add(((Node)event.getSource()).getScene());
		
		root = FXMLLoader.load(getClass().getResource("followers.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// switch to MY page - tweets & replies
	public void switchToMyTweetsNReplies(ActionEvent event) throws IOException{
		SceneData.MyPage_tweets = ((Node)event.getSource()).getScene();
		
		if (SceneData.MyPage_tweetsnreplies == null) {
			root = FXMLLoader.load(getClass().getResource("myinfo-tnr.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = SceneData.MyPage_tweetsnreplies;
			stage.setScene(scene);
			stage.show();
		}
	}
		
	// switch to MY page - likes
	public void switchToMyLikes(ActionEvent event) throws IOException{
		SceneData.MyPage_tweets = ((Node)event.getSource()).getScene();
		
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

		
		// set Tweets 
		// fill Tweets
		List<Post> posts = new ArrayList<>(getPosts());

		for(Post post: posts){

			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post.fxml"));

				VBox newBox = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPost(post.getNickname(), post.getUser_id(), post.getContent(), post.getImg(), post.getLike_num(), post.getCommnet_num(), post.getRetweet_num(),  post.getPostid());

				PostsContainer.getChildren().add(newBox);


			} catch (Exception e){
				e.printStackTrace();
			}
		}


	}

	public List<Post> getPosts(){
		List<Post> posts = new ArrayList<Post>();

		Model model = new Model();
		posts = model.getMainUserPost();

		return  posts;
	}
}
