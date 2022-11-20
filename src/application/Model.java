package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Model {

	// hash map param
	public void signUp(HashMap<String, String> info) {
		// sign up
	}

	
	
	public void tweet(String content) {
		// sql connection
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            con = DriverManager.getConnection(url, sql_id, pw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        // sql operation

        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
        	String text = null;
    		String p_id = null;
    		
    		stmt = con.createStatement();
    		// to make post_id, need to know number of post
    		String sql = "select count(post_id) from post group by user_id having user_id = \'" + LocalUser.id + "\'";
    		rs = stmt.executeQuery(sql);
    		if (rs.next()) {
    			String p_num = String.valueOf(rs.getInt(1) + 1);
    			p_id = "p" + p_num;
    		}
    		else {
    			p_id = "p1";
    		}

    		text = content;
    		sql = "insert into post (post_id, user_id, date, content) values (\'" 
    				+ p_id + "\', \'" + LocalUser.id + "\', now(), \"" + text + "\")" ;
    		pstm = con.prepareStatement(sql);
    		pstm.executeUpdate();
    		
        } catch (Exception e) {
            e.printStackTrace();
        }

        // operation end
        try {
            if (stmt != null && !stmt.isClosed()) stmt.close();
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
	}

    public boolean login(String id, String pwd) {
        // sql connection

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            con = DriverManager.getConnection(url, sql_id, pw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        // sql operation

        boolean ret = false;

        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            stmt = con.createStatement();
            String s1 = "select user_id from user where user_id = \'" + id + "\' and password = \'" + pwd + "\'";
            rs = stmt.executeQuery(s1);

            if (rs.next()) {
				 /*System.out.println("Logged in! Welcome " + id + "!");
				 loggedIn = 1;*/
                ret = true;
            } else {
				/*System.out.println("Wrong ID / Password. Try again!");
				id = null;
				pwd = null;*/
                ret = false;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // operation end
        try {
            if (stmt != null && !stmt.isClosed()) stmt.close();
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return ret;

    }


    // update user
    public void setUser(String id) {
        // sql connection

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            con = DriverManager.getConnection(url, sql_id, pw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        LocalUser.id = id;


        // sql operation
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        int count = 1;

        try {

            // set name
            stmt = con.createStatement();
            sql = "select name from user where user_id = \'" + LocalUser.id + "\'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String result = rs.getString(1);
                LocalUser.name = result;

            }


            // show followings

            // System.out.println("==== You follows below users ====");
            stmt = con.createStatement();
            sql = "select target_user_id from follows where user_id = \'" + LocalUser.id + "\'";
            rs = stmt.executeQuery(sql);
            count = 1;
            while (rs.next()) {

                String result = rs.getString(1);
                // ������ �ʿ� �����ϱ�.
                //System.out.println(count + ". " + result);
                count++;
            }


            // show follower
            // System.out.println("==== Your followers ====");
            stmt = con.createStatement();
            sql = "select user_id from follows where target_user_id = \'" + id + "\'";
            rs = stmt.executeQuery(sql);
            count = 1;
            while (rs.next()) {
                String result = rs.getString(1);
                // no need to show
                // System.out.println(count + ". " + result);
                count++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        // operation end
        try {
            if (stmt != null && !stmt.isClosed()) stmt.close();
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    }


    public List<Post> getMainUserPost() {
        return getUserPosts(LocalUser.id);
    }


    public List<Post> getUserPosts(String userid) {
        List<Post> ls = new ArrayList<Post>();


        // sql connection

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            con = DriverManager.getConnection(url, sql_id, pw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        try {


            String query1 = "SELECT * FROM twitter.post WHERE user_id =? ORDER BY date desc;";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, userid);
            ResultSet rs = ps1.executeQuery();

            while (rs.next()) {
                String p_id = rs.getString("post_id");

                String p_u_id = rs.getString("user_id");
                Date p_date = rs.getDate("date");
                String p_content = rs.getString("content");

                String query2 = "SELECT name FROM User WHERE user_id =?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, p_u_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }

                int p_num_of_likes = rs.getInt("num_of_likes");
                int p_num_of_comments = rs.getInt("num_of_comments");
                int p_num_of_retweets= rs.getInt("num_of_retweets");

                Post a_post = new Post();
                a_post.setNickname(username);
                a_post.setUser_id(p_u_id);
                a_post.setContent(p_content);
                a_post.setDate(p_date);
				a_post.setLike_num(p_num_of_likes);
				a_post.setCommnet_num(p_num_of_comments);
                a_post.setRetweet_num(p_num_of_retweets);
                a_post.setPostid(p_id);
                ls.add(a_post);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }

    public List<Post> getRandomPosts() {
        List<Post> ls = new ArrayList<Post>();


        // sql connection

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            con = DriverManager.getConnection(url, sql_id, pw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        // sql operation

        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {

            stmt = con.createStatement();
            String query = "SELECT * FROM \n" +
                    "(\n" +
                    "    SELECT * FROM post ORDER BY rand() LIMIT 5\n" +
                    ") T1\n" +
                    "ORDER BY date DESC;";
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();


            while (rs.next()) {
                String p_id = rs.getString("post_id");

                String p_u_id = rs.getString("user_id");
                Date p_date = rs.getDate("date");
                String p_content = rs.getString("content");

                String query2 = "SELECT name FROM User WHERE user_id =?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, p_u_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }

                int p_num_of_likes = rs.getInt("num_of_likes");
                int p_num_of_comments = rs.getInt("num_of_comments");
                int p_num_of_retweets= rs.getInt("num_of_retweets");

                Post a_post = new Post();
                a_post.setNickname(username);
                a_post.setUser_id(p_u_id);
                a_post.setContent(p_content);
                a_post.setDate(p_date);
                a_post.setLike_num(p_num_of_likes);
                a_post.setCommnet_num(p_num_of_comments);
                a_post.setRetweet_num(p_num_of_retweets);
                a_post.setPostid(p_id);
                ls.add(a_post);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // operation end
        try {
            if (stmt != null && !stmt.isClosed()) stmt.close();
            if (rs != null && !rs.isClosed()) rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return ls;

    }

    public List<Post> getUserLikedPosts(String userid) {
        List<Post> ls = new ArrayList<Post>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            Connection con = DriverManager.getConnection(url, sql_id, pw);
            String query1 = "SELECT * FROM twitter.post WHERE (post_id IN (SELECT post_id FROM twitter.likes WHERE user_id = ?) AND user_id IN (SELECT target_user_id FROM twitter.likes WHERE user_id = ?))  ORDER BY date desc;";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, userid);
            ps1.setString(2, userid);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                String p_id = rs.getString("post_id");

                String p_u_id = rs.getString("user_id");
                Date p_date = rs.getDate("date");
                String p_content = rs.getString("content");

                String query2 = "SELECT name FROM User WHERE user_id =?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, p_u_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }

                int p_num_of_likes = rs.getInt("num_of_likes");
                int p_num_of_comments = rs.getInt("num_of_comments");
                int p_num_of_retweets = rs.getInt("num_of_retweets");

                Post a_post = new Post();
                a_post.setNickname(username);
                a_post.setUser_id(p_u_id);
                a_post.setContent(p_content);
                a_post.setDate(p_date);
                a_post.setLike_num(p_num_of_likes);
                a_post.setCommnet_num(p_num_of_comments);
                a_post.setRetweet_num(p_num_of_retweets);
                a_post.setPostid(p_id);
                ls.add(a_post);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;

    }

    public Post getPostDetail(String postid, String userid) {
        Post post = new Post();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            Connection con = DriverManager.getConnection(url, sql_id, pw);
            String query1 = "SELECT * FROM twitter.post WHERE post_id = ? AND user_id = ?";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, postid);
            ps1.setString(2, userid);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                String p_id = rs.getString("post_id");

                String p_u_id = rs.getString("user_id");
                Date p_date = rs.getDate("date");
                String p_content = rs.getString("content");

                String query2 = "SELECT name FROM User WHERE user_id =?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, p_u_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }

                int p_num_of_likes = rs.getInt("num_of_likes");
                int p_num_of_comments = rs.getInt("num_of_comments");
                int p_num_of_retweets = rs.getInt("num_of_retweets");

                post.setNickname(username);
                post.setUser_id(p_u_id);
                post.setContent(p_content);
                post.setDate(p_date);
                post.setLike_num(p_num_of_likes);
                post.setCommnet_num(p_num_of_comments);
                post.setRetweet_num(p_num_of_retweets);
                post.setPostid(p_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return post;

    }

    public List<User> SearchUser(String searchText) {
        List<User> ls = new ArrayList<User>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            Connection con = DriverManager.getConnection(url, sql_id, pw);
            String query1 = "select * from user where user_id LIKE ?;";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, searchText + "%");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String user_id = rs.getString("user_id");

                User newUser = new User();
                newUser.setName(name);
                newUser.setUser_id(user_id);

                ls.add(newUser);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }
    public List<User> getFollowerById(String userid) {
        List<User> ls = new ArrayList<User>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            Connection con = DriverManager.getConnection(url, sql_id, pw);
            String query1 = "select * from follows where target_user_id = ?;";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, userid);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString("user_id");

                String query2 = "SELECT name FROM User WHERE user_id =?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, user_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }
    public List<User> getFollowingById(String userid) {
        List<User> ls = new ArrayList<User>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            Connection con = DriverManager.getConnection(url, sql_id, pw);
            String query1 = "select * from follows where user_id = ?;";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, userid);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString("target_user_id");

                String query2 = "SELECT name FROM User WHERE user_id =?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, user_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }

                User newUser = new User();
                newUser.setName(username);
                newUser.setUser_id(user_id);

                ls.add(newUser);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls;
    }

    public User getUserInfo(String userid){
        User user = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public Boolean like_post(){

        Boolean IsSuccess = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String sql_id = "root", pw = "12341234";
            Connection con = DriverManager.getConnection(url, sql_id, pw);
            String query1 = "select * from follows where user_id = ?;";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, LocalUser.id);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                String user_id = rs.getString("target_user_id");
                String query2 = "UPDATE twitter.post SET num_of_likes = (select count(like_id) from likes where target_user_id = ? and post_id = ?) WHERE user_id = ? AND post_id = ?;";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, user_id);
                ResultSet rs2 = ps2.executeQuery();

                String username = null;
                if (rs2.next()) {
                    username = rs2.getString("name");
                }

                User newUser = new User();
                newUser.setName(username);
                newUser.setUser_id(user_id);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return IsSuccess;
    }

}

