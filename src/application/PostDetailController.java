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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PostDetailController implements Initializable{

    @FXML
    public Label name;
    @FXML
    public Label user_id;
    @FXML
    public Label content;
    @FXML
    public ImageView img;
    @FXML
    public Label num_of_likes;
    @FXML
    public Label num_of_comments;
    @FXML
    public ImageView iLike;
    @FXML
    public ImageView iRetweet;
    
    
    @FXML
    public void likePost(ActionEvent evnet) {
    	iLike.setVisible(true);
    }
    
    public void retweetPost(ActionEvent evnet) {
    	iRetweet.setVisible(true);
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
    
    public void switchToUserInfo(ActionEvent event) throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("OtherInfo-tweets.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
    
    
    public void setPost(String name, String user_id, String content, Image img, Integer num_of_likes, Integer num_of_comments) {
        this.name.setText(name);
        this.user_id.setText("@" + user_id);
        this.content.setText(content);
        this.img.setImage(img);
        this.num_of_likes.setText(String.valueOf(num_of_likes));
        this.num_of_comments.setText(String.valueOf(num_of_comments));
    }
    
    
    
 // switch to main page
 	public void switchToMain(ActionEvent event) throws IOException{

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
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// set post
    }

}
    
    
    
    
    
    
    

