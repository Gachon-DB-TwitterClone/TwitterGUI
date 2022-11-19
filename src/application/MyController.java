package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public class MyController {
    @FXML
    private Label main_uid;

    public void setId(String id) {
        main_uid.setText('@' + id);
    }

    public void alertNotLoggedIn(ActionEvent event) {
        AlertBox.display("Alert", "You need to log in! Click bird image to log in.");
    }


    public List<Post> getPosts() {
        List<Post> ls = new ArrayList<>();

        Post post;

        Model model = new Model();

        List<Post> posts = model.getMainUserPost();




        return ls;
    }


}	
