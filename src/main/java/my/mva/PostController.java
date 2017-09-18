package my.mva;

import my.mva.libs.*;
import my.mva.helpers.*;

import net.sf.xsshtmlfilter.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

  @GetMapping("/post")
  public String postForm(Model model) {
    model.addAttribute("user_id", 1);
    return "post";
  }

  @PostMapping("/post")
  //public String post(@ModelAttribute ArticleInfo model) {
  public String post(HttpServletRequest request, Model model) {
    HTMLFilter hh = new HTMLFilter();
    // create new article
    Article content = new Article();
    try {
      content.setId(Integer.parseInt(request.getParameter("user_id")));
      content.setTitle(request.getParameter("title"));
      content.setContent(request.getParameter("content"));
      // write article to database
      content.write();
      model.addAttribute("info", "<p>Preview of article:</p><p>" + content.getContent() + "</p>");
      model.addAttribute("msg", "Your article has been registered");
      model.addAttribute("title", "Home");
      model.addAttribute("page", "/");
    } catch (NumberFormatException e) {
      System.err.println(e.getMessage());
    }
    return "redirect";
  }
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
