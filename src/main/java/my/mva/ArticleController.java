package my.mva;

import my.mva.libs.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;

@Controller
public class ArticleController {

    @GetMapping("/article")
    public String article(HttpServletRequest request, Model model) {
    //public String article(@RequestParam(value="id") String id, Model model) {
      String id = request.getParameter("id");

      if ( id.length()>0 ) {
        Article art = new Article(Integer.parseInt(id));
        model.addAttribute("id", id);
        model.addAttribute("title", art.getTitle());
        model.addAttribute("date_created", art.getDateCreated());
        model.addAttribute("username", art.getUsername());
        model.addAttribute("content", art.getContent());
        model.addAttribute("comments", this.getAllComments(id));
        return "article";
      } else {
        return "redirect";
      }
    }

    public List<Comment> getAllComments(String id) {
      DB db = DB.getInstance();
      List<Comment> arr = new ArrayList<Comment>();
      String sql = "SELECT * FROM comment WHERE article_id = '" + id + "' ORDER BY date_created";
      ResultSet res = db.query(sql);
      try {
        while (res.next()) {
          int cid = res.getInt("id");
          String cmt = res.getString("comment");
          int art_id = res.getInt("article_id");
          String d = res.getString("date_created");
          arr.add(new Comment(cid, cmt, art_id, d));
        }
        return arr;
      } catch (SQLException se) {
        System.err.println(se.getMessage());
      }
      return null;
    }
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
