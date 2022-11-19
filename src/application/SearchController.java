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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchController implements Initializable {


	// �̿ϼ� ��� �ұ� ���ͷ� ��ġ
	@FXML
	private TextField s_u_id;
	
	//
	// Scene Controller
	// 
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	
	// switch to main page
	
	public void switchToMain(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}



	public VBox UsersContainer;

	public List<User> getUsers(String userid){
		List<User> users = new ArrayList<User>();

		Model model = new Model();
		users = model.SearchUser(userid);


		return users;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		s_u_id.textProperty().addListener((observable, oldValue, newValue) -> {
			UsersContainer.getChildren().removeAll(UsersContainer.getChildren());
			if(!newValue.equals("")){
				List<User> users = new ArrayList<>(getUsers(newValue));

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

		});
	}
}
