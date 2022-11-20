package application;

import javafx.scene.image.Image;

import java.util.Date;

public class Post {

    private String username;
    private String user_id;
    private String content;
    private Image img;
    private int like_num;
    private int commnet_num;
    private int retweet_num;
    private Date date;

    private String postid;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getCommnet_num() {
        return commnet_num;
    }

    public void setCommnet_num(int commnet_num) {
        this.commnet_num = commnet_num;
    }

    public int getRetweet_num() {
        return retweet_num;
    }

    public void setRetweet_num(int retweet_num) {
        this.retweet_num = retweet_num;
    }

}