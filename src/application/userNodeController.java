package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class userNodeController {
    public Label name;
    public Label user_id;

    //
	// Scene Controller
	// 
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    public void switchToUserInfo(MouseEvent event) throws IOException{
		
    	SceneData.PrevScene = ((Node)event.getSource()).getScene();
    	
		root = FXMLLoader.load(getClass().getResource("UserInfo-tweets.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
    
    
    public void setUserNode(String name, String user_id) {
        this.name.setText(name);
        this.user_id.setText("@" + user_id);
    }
}
