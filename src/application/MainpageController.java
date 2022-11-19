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

public class MainpageController implements Initializable {
    @FXML
    private Label main_uid;

    public VBox PostsContainer;

    //
    // Scene Controller
    //

    private Stage stage;
    private Scene scene;
    private Parent root;

    // switch to my page
    public void switchToMy(ActionEvent event) throws IOException {

//			SceneData.MainScene = ((Node)event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("myinfo-tweet.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // switch to search page
    public void switchToSearch(ActionEvent event) throws IOException {

//		SceneData.MainScene = ((Node)event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("search.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // switch to writh_post page
    public void switchToWritepost(ActionEvent event) throws IOException {

//		SceneData.MainScene = ((Node)event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("WritePost.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        main_uid.setText('@' + LocalUser.id);

        List<Post> posts = new ArrayList<>(getPosts());

        for (Post post : posts) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("post.fxml"));

                VBox newBox = fxmlLoader.load();
                PostController postController = fxmlLoader.getController();
                postController.setPost(post.getNickname(), post.getUser_id(), post.getCaption(), post.getImg(), post.getLike_num(), post.getCommnet_num());

                PostsContainer.getChildren().add(newBox);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<Post>();

        Model model = new Model();
        posts = model.getRandomPost();

        return posts;
    }


    public void refresh(ActionEvent event) {
        try {

            Stage stage;
            Scene scene;
            Parent root;

            root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
