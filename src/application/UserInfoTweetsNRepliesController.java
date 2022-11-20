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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInfoTweetsNRepliesController implements Initializable {

    public Button btnFollow;

    public void follow(ActionEvent event) {
        String fol = btnFollow.getText();

        //if user clicked follow btn, change to following
        if (fol.equalsIgnoreCase("follow")) {

            btnFollow.setText("Following");
            btnFollow.setStyle("-fx-background-color: white; -fx-background-radius: 30px; -fx-border-color: #C0C0C0; -fx-border-radius: 30px; -fx-text-fill: black");

        }
        // else if user clicked following btn to unfollow, change to follow

        if (fol.equalsIgnoreCase("following")) {

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

    public void switchPrevScene(ActionEvent event) throws IOException {

        if (SceneData.PrevScene == ((Node) event.getSource()).getScene()) {
            root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = SceneData.PrevScene;
            stage.setScene(scene);
            stage.show();
        }

    }


    public void switchToFollowing(ActionEvent event) throws IOException {

        SceneData.PrevScene = ((Node) event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("following.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFollowers(ActionEvent event) throws IOException {

        SceneData.PrevScene = ((Node) event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("followers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTweets(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("UserInfo-tweets.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLikes(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("UserInfo-likes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // switch to writh_post page
    public void switchToWritepost(ActionEvent event) throws IOException {

        SceneData.PrevScene = ((Node) event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("WritePost.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // switch to main page
    public void switchToMain(ActionEvent event) throws IOException {

        SceneData.PrevScene = ((Node) event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // switch to search page
    public void switchToSearch(ActionEvent event) throws IOException {

        SceneData.PrevScene = ((Node) event.getSource()).getScene();

        root = FXMLLoader.load(getClass().getResource("search.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public Label u_id;
    public Label u_name;
    public Label following;
    public Label follower;

    public VBox vbox;

    public VBox PostsContainer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Model model = new Model();
        u_id.setText(LocalUser.seeing_userid);
        u_name.setText(LocalUser.seeing_username);
        following.setText(String.valueOf((model.getFollowingById(LocalUser.seeing_userid).size())));
        follower.setText(String.valueOf((model.getFollowerById(LocalUser.seeing_userid).size())));

        // set Tweets
        // fill Tweets


        // set Tweets
        // fill Tweets
        List<Post> posts = new ArrayList<>(getPosts());

        for (Post post : posts) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("post.fxml"));

                VBox newBox = fxmlLoader.load();
                PostController postController = fxmlLoader.getController();
                postController.setPost(post.getNickname(), post.getUser_id(), post.getContent(), post.getImg(), post.getLike_num(), post.getCommnet_num(), post.getRetweet_num(), post.getPostid());

                PostsContainer.getChildren().add(newBox);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<Post>();

        Model model = new Model();
        posts = model.getUserLikedPosts(LocalUser.seeing_userid);

        return posts;
    }
}
