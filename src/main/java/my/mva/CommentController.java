package my.mva;

import my.mva.libs.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @GetMapping("/comment")
    public String show(HttpServletRequest request, Model model) {
      String id = request.getParameter("article_id");
      if ( id.length() > 0 ) {
        Article art = new Article(Integer.parseInt(id));
        model.addAttribute("title", art.getTitle());
        model.addAttribute("article_id", id);
      }
      return "comment";
    }

    @PostMapping("/comment")
    public String save(HttpServletRequest request, Model model) {
      String id = request.getParameter("article_id");
      String comment = request.getParameter("comment");

      Comment cmt = new Comment();
      cmt.setArticleId(Integer.parseInt(id));
      cmt.setComment(comment);
      cmt.write();

      model.addAttribute("info", "<p>Preview of article:</p><p>" + cmt.getComment() + "</p>");
      model.addAttribute("msg", "Your comment has been registered");
      model.addAttribute("title", "Home");
      model.addAttribute("page", "/");
      return "redirect";
    }
}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
