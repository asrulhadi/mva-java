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

public class Comment implements Serializable {
  private int commentId;
  private String comment;
  private int articleId;
  private String dateCreated;

  public Comment() {
  }

  public Comment(int id, String cmt, int a_id, String sdate) {
    this.commentId = id;
    this.comment = cmt;
    this.articleId = a_id;
    this.dateCreated = sdate;
  }

  public int getCommentId() {
    return this.commentId;
  }

  public void setCommentId(int id) {
    this.commentId = id;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String cmt) {
    this.comment = cmt;
  }

  public int getArticleId() {
    return this.articleId;
  }

  public void setArticleId(int id) {
    this.articleId = id;
  }

  public String getDateCreated() {
    return this.dateCreated;
  }

  public void setDateCreated(String newDate) {
    this.dateCreated = newDate;
  }

  public void write() {

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String date_full = dateFormat.format(new java.util.Date());

    DB db = DB.getInstance();
    //    String sql = "INSERT INTO comment (comment, article_id, date_created) values " +
    //      "( '" + this.comment + "'" +
    //      ", '" + this.articleId + "'" +
    //      ", '" + date_full + "')";
    //    boolean result = db.update(sql);
       String s = "INSERT INTO comment (comment, article_id, date_created)";
       s += " Values (?,?,?)";
     try {
       PreparedStatement ps = db.prepareStatement(s);
       ps.setString(1,this.comment);
       ps.setInt(2, this.articleId);
       ps.setString(3, date_full);
       int res = ps.executeUpdate();
       if (res>0) {
       }
      } catch (SQLException se) {
      }
  }

}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
