package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestMainpageController implements Initializable {


	public void alertNotLoggedIn(ActionEvent event) {
		AlertBox.display("Alert", "You need to log in! Click bird image to log in.");
	}
	//
	// Scene Controller
	// 
	private Stage stage;
	private Scene scene;
	private Parent root;
	public VBox PostsContainer;


	// switch to log in page
	
	public void switchToLogin(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		List<Post> posts = new ArrayList<>(getPosts());

		for(Post post: posts){

			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post.fxml"));

				VBox newBox = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPost(post.getNickname(), post.getUser_id(), post.getCaption(), post.getImg(), post.getLike_num(), post.getCommnet_num(), post.getRetweet_num());

				PostsContainer.getChildren().add(newBox);


			} catch (Exception e){
				e.printStackTrace();
			}
		}


	}

	public List<Post> getPosts(){
		List<Post> posts = new ArrayList<Post>();

		Model model = new Model();
		posts = model.getRandomPost();

		return  posts;
	}


	public void refresh(ActionEvent event) {
		try {

			Stage stage;
			Scene scene;
			Parent root;

			root = FXMLLoader.load(getClass().getResource("Guest_MainPage.fxml"));

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
