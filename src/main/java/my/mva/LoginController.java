package my.mva;

import my.mva.libs.*;
import my.mva.helpers.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
      model.addAttribute("logininfo", new LoginInfo());
      return "login";
    }

    @PostMapping("/login")
    //public String loginUser(@ModelAttribute LoginInfo logininfo, Model model) {
    public String loginUser(HttpServletRequest request, @ModelAttribute LoginInfo logininfo, Model model) {
      User user = new User();
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      user.login(username, password, request.getSession());

      if (user.getId()<=0) {
        // error in auth
        model.addAttribute("error", "Username or Password incorrect");
        return "login";
      }
      ///// debugging
      HttpSession sess = request.getSession(true);
      System.out.println("*******DEBUG*******");
      System.out.println("Succesfully Login");
      System.out.println("Request Session ID: " + request.getRequestedSessionId());
      System.out.println("        Session ID: " + sess.getId());
      List names = Collections.list(sess.getAttributeNames());
      for (Object n:names) {
        System.out.println("Session: " + n + " => " + sess.getAttribute((String)n));
      }
      System.out.println("**** END DEBUG ****");
      ///// end
//return "redirect://";
      return "index";
    }
}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
