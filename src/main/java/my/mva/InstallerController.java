package my.mva;

import my.mva.libs.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InstallerController {

  @RequestMapping("/install")
  public String install(@RequestParam(value="install", required=false, defaultValue="no") String install, Model model) {
    model.addAttribute("install", install);
    if ( install.equals("yes") ) {
      String ok = "OK";
      DB db = DB.getInstance();

      if (db.createDB()>0) model.addAttribute("CreateDB",ok);

      if ( db.execute("DROP TABLE IF EXISTS user;")==0 &&
          db.execute("DROP TABLE IF EXISTS article;")==0 &&
          db.execute("DROP TABLE IF EXISTS comment;")==0) model.addAttribute("DropTables",ok);

      String sql = "CREATE TABLE user (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(16), password VARCHAR(64), avatar VARCHAR(128), admin BOOLEAN)";
      if (db.execute(sql)==0) model.addAttribute("CreateUser",ok);

      sql = "CREATE TABLE article (id INT AUTO_INCREMENT PRIMARY KEY, user_id INT, title VARCHAR(140), content TEXT, date_created TIMESTAMP)";
      if (db.execute(sql)==0) model.addAttribute("CreateArticle",ok);

      sql = "CREATE TABLE comment (id INT AUTO_INCREMENT PRIMARY KEY, comment VARCHAR(140), article_id INT, date_created TIMESTAMP)";
      if (db.execute(sql)==0) model.addAttribute("CreateComment",ok);

      String user = "INSERT INTO user (username,password,avatar,admin) VALUES (?,?,?,?)";
      PreparedStatement ps = db.prepareStatement(user);
      // Alice
      try {
        ps.setString(1,"Alice");
        ps.setString(2,"p455w0rd");
        ps.setString(3,"alice.jpg");
        ps.setBoolean(4,true);
        if (ps.executeUpdate()>0) model.addAttribute("Alice", ok);
      } catch (SQLException e) {
      }

      // Bob
      try {
        ps.setString(1,"Bob");
        ps.setString(2,"DualPhone");
        ps.setString(3,"bob.jpg");
        ps.setBoolean(4,false);
        if (ps.executeUpdate()>0) model.addAttribute("Bob", ok);
      } catch (SQLException e) {
      }

      // Eve
      try {
        ps.setString(1,"Eve");
        ps.setString(2,"Hacker");
        ps.setString(3,"eve.jpg");
        ps.setBoolean(4,false);
        if (ps.executeUpdate()>0) model.addAttribute("Eve", ok);
      } catch (SQLException e) {
      }
    }
    return "install";
  }
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
