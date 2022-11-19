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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FollowingController implements Initializable {
	
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
	public void switchToProfile(ActionEvent event) throws IOException{
		if(LocalUser.seeing_userid.equals(LocalUser.id)){
			root = FXMLLoader.load(getClass().getResource("myinfo-likes.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			root = FXMLLoader.load(getClass().getResource("UserInfo-likes.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}
	
	public void switchToFollowers(ActionEvent event) throws IOException{

		root = FXMLLoader.load(getClass().getResource("followers.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public VBox UsersContainer;


	public List<User> getFollowingById(String userid){
		List<User> users = new ArrayList<User>();

		Model model = new Model();
		users = model.getFollowingById(userid);


		return users;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		userName.setText(LocalUser.seeing_userid);

		List<User> users = new ArrayList<>(getFollowingById(LocalUser.seeing_userid));

		for(User user: users){

			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("user_node.fxml"));

				VBox newBox = fxmlLoader.load();
				userNodeController userNodeController = fxmlLoader.getController();
				userNodeController.setUserNode(user.getName(), user.getUser_id());
				UsersContainer.getChildren().add(newBox);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
