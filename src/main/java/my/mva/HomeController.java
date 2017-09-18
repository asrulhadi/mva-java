package my.mva;

import my.mva.libs.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(HttpServletRequest request, Model model) {
      List<Article> articles = this.getAllArticles();
      model.addAttribute("articles", articles);

      ///// debugging
      HttpSession sess = request.getSession(true);
      System.out.println("*******DEBUG*******");
      System.out.println("Request Session ID: " + request.getRequestedSessionId() + 
          " => " + (request.isRequestedSessionIdValid()?"Valid":"Not Valid"));
      System.out.println("        Session ID: " + sess.getId());
      List names = Collections.list(sess.getAttributeNames());
      for (Object n:names) {
        System.out.println("Session: " + n + " => " + sess.getAttribute((String)n));
      }
      System.out.println("**** END DEBUG ****");
      ///// end

      return "index";
    }

    public List<Article> getAllArticles() {
      DB db = DB.getInstance();
      String sql = "SELECT * FROM article;";
      ResultSet result = db.query(sql);
      List<Article> art = new ArrayList<Article>();

      try {
        while (result.next()) {
          Article a = new Article(
                result.getInt("id"),
                result.getInt("user_id"),
                result.getString("title"),
                result.getString("content"),
                result.getString("date_created")
          );
          art.add(a);
        }
      } catch (NullPointerException np) {
        System.err.println(np.getMessage());
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
      return art;
    }

}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
