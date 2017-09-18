package my.mva.libs;

import java.sql.*;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class User {
  private int id = -1;
  private String username = null;
  private String password = null;
  private String avatar = null;
  private boolean admin = false;


  /**
   * Default empty User constructor
   */
  public User() {
    super();
    if (this.checkUserSession()) {
      //this.set_username($_SESSION['username']);
      //this.set_user_id($_SESSION['user_id']);
    }
  }

  /**
   * Default User constructor
   */
  public User(int id, String username, String password) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
  }

  /**
   * Returns value of id
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Sets new value of id
   * @param
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns value of username
   * @return
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets new value of username
   * @param
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Returns value of password
   * @return
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets new value of password
   * @param
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns value of avatar
   * @return
   */
  public String getAvatar() {
    return avatar;
  }

  /**
   * Sets new value of avatar
   * @param
   */
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  /**
   * Returns value of admin
   * @return
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * Sets new value of admin
   * @param
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  /**
   * This function will check a user submitted username and password against
   * the database. If it exists, it sets up a new session
   */
  public boolean login(String user, String password, HttpSession session) {
    DB db = DB.getInstance();
    String sql = "select * from user where username = '" +  user + "' and password = '" + password + "'";
    ResultSet res = db.query(sql);
    try {
      if (res.next()) {
        this.setId(res.getInt("id"));
        this.setUsername(res.getString("username"));
        this.setAvatar(res.getString("avatar"));
        this.setAdmin(res.getBoolean("admin"));
        // create session
        this.createUserSession(session);
        return true;
      }
    } catch (SQLException se) {
      System.err.println(se.getMessage());
    }
    return false;
  }

  public boolean createUserSession(HttpSession session) {
    // This function is used to start a session and set initial variables
    // session_start();
    // $_SESSION['user_id'] = $this->get_user_id();
    // $_SESSION['username'] = $this->get_username();
    session.setAttribute("user_id", this.id);
    session.setAttribute("username", this.username);
    session.setAttribute("avatar", this.avatar);
    session.setAttribute("admin", this.admin);
    return true;
  }

  // This function checks to see if a user is logged in.
  public boolean checkUserSession() {
    // session_start();
    // if(array_key_exists('user_id',$_SESSION)
    // && array_key_exists('username',$_SESSION)) {
    // 	return true;
    // }
    // return false;
    return false;
  }
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
