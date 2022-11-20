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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PostController implements Initializable {

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
    public Label num_of_retweets;
    @FXML
    public ImageView iLike;
    @FXML
    public ImageView iRetweet;


    @FXML
    public void likePost(ActionEvent evnet) {
        Model model = new Model();
        Boolean IsLiked = model.like_post(LocalUser.id, user_id.getText(), this.post_id);
        iLike.setVisible(IsLiked);
        if(IsLiked) {
            int new_num_likes = Integer.parseInt(num_of_likes.getText()) + 1;
            num_of_likes.setText(String.valueOf(new_num_likes));
        } else {
            int new_num_likes = Integer.parseInt(num_of_likes.getText()) - 1;
            num_of_likes.setText(String.valueOf(new_num_likes));
        }
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

    private String post_id;


    public void switchToUserInfo(MouseEvent event) throws IOException {
        LocalUser.seeing_userid = user_id.getText();
        LocalUser.seeing_postid = this.post_id;
        LocalUser.seeing_username = name.getText();

        SceneData.PrevScene.add(((Node) event.getSource()).getScene());

        root = FXMLLoader.load(getClass().getResource("UserInfo-tweets.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToPostDetail(MouseEvent event) throws IOException {
        LocalUser.seeing_userid = user_id.getText();
        LocalUser.seeing_postid = this.post_id;
        LocalUser.seeing_username = name.getText();


        SceneData.PrevScene.add(((Node) event.getSource()).getScene());

        root = FXMLLoader.load(getClass().getResource("post-detail.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setPost(String name, String user_id, String content, Image img, Integer num_of_likes, Integer num_of_comments, Integer num_of_retweets, String post_id) {
        this.name.setText(name);
        this.user_id.setText(user_id);
        this.content.setText(content);
        if (img != null) {
            this.img.setImage(img);
        } else {
            this.img.setVisible(false);
            this.img.setManaged(false);
        }
        this.num_of_likes.setText(String.valueOf(num_of_likes));
        this.num_of_comments.setText(String.valueOf(num_of_comments));
        this.num_of_retweets.setText(String.valueOf(num_of_retweets));
        this.post_id = post_id;
        Model model = new Model();

//        public Boolean isLiked(String user_id, String target_user_id, String post_id){
        iLike.setVisible(model.isLiked(LocalUser.id, user_id, post_id));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
