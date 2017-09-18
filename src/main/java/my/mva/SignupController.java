package my.mva;

import my.mva.libs.*;
import my.mva.helpers.*;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

    @GetMapping("/signup")
    public String loginForm(Model model) {
      //List<Article> articles = this.getAllArticles();
      model.addAttribute("logininfo", new LoginInfo());
      model.addAttribute("error", "Di manakah");
      return "signup";
    }

    @PostMapping("/signup")
    public String loginUser(@ModelAttribute LoginInfo model) {

      //request.setAttribute("articles",articles);
      //model.addAttribute("name", "index");
      //model.addAttribute("articles", articles);
      return "login";
    }

    // @RequestMapping("/home")
    // public String home(@RequestParam(value="name", required=false, defaultValue="Home") String name, Model model) {
    //     model.addAttribute("name", name);
    //     return "home";
    // }
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
