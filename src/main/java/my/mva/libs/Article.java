/**
 * Part of Web Application Secure Coding - JSF
 * It is extremely insecure! Please do not use
 * this in any kind of production environment
 *
 * @author asrulhadi
 * @create 2015
 */
package my.mva.libs;

import java.sql.*;
import java.util.*;
import java.text.*;
import javax.sql.DataSource;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Article implements Serializable {

  private int id;
  private int userId;
  private String username;
  private String title;
  private String content;
  private String dateCreated;

  private int called = 0;

  public Article() {
  }

  public Article(int _id) {
    this.id = _id;
    this.retrieve_content();
  }

  public Article(int _user_id, String _title, String _content, String _date_created) {
      this.userId = _user_id;
      this.title = _title;
      this.content = _content;
      this.dateCreated = _date_created;
  }

  public Article(int _id, int _user_id, String _title, String _content, String _date_created) {
      this.id = _id;
      this.userId = _user_id;
      this.title = _title;
      this.content = _content;
      this.dateCreated = _date_created;
  }

  public void getArticle(int article_id) {
    this.id = article_id;
    this.retrieve_content();
  }

  public int getId() {
    return this.id;
  }

  public void setId(int article_id) {
    this.id = article_id;
  }

  // retrieve username from database
  public String updateUsername() {
    String name = "None";
    DB db = DB.getInstance();
    String sql = "SELECT username FROM user WHERE id = " + this.userId;
    ResultSet result = db.query(sql);
    try {
      result.next();
      name = result.getString("username");
      this.username = name;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return name;
  }
  public String getUsername() {
      return this.username;
  }
  public void setUsername(String s) {
    this.username = this.updateUsername();
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int _user_id) {
    this.userId = _user_id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String _title) {
    this.title = _title;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String _content) {
    this.content = _content;
  }

  public String getDateCreated() {
    return this.dateCreated;
  }

  private void setDateCreated(String _date_created) {
    this.dateCreated = _date_created;
  }

  private boolean retrieve_content() {
    DB db = DB.getInstance();
    String sql = "SELECT * FROM article WHERE id = " + this.id;
    ResultSet result = db.query(sql);
    try {
      result.next();
      this.setId(result.getInt("user_id"));
      this.setTitle(result.getString("title"));
      this.setContent(result.getString("content"));
      this.setDateCreated(result.getString("date_created"));
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return true;
  }

  public boolean write() {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String date_full = dateFormat.format(new java.util.Date());

    DB db = DB.getInstance();
    String sql = "INSERT INTO article (user_id, title, content, date_created) values (" +
      "'" + this.userId + "'," +
      "'" + this.title + "'," +
      "'" + this.content + "'," +
      "'" + date_full + "')";
    db.update(sql);
    return true;
  }

}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
