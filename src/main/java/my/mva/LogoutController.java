package my.mva;

import my.mva.libs.*;
import my.mva.helpers.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
      ///// debugging
      {
      HttpSession sess = request.getSession(true);
      System.out.println("*******DEBUG*******");
      System.out.println("Before change session ID");
      System.out.println("Request Session ID: " + request.getRequestedSessionId());
      System.out.println("        Session ID: " + sess.getId());
      List names = Collections.list(sess.getAttributeNames());
      for (Object n:names) {
        System.out.println("Session: " + n + " => " + sess.getAttribute((String)n));
      }
      System.out.println("**** END DEBUG ****");
      }
      ///// end
      //request.changeSessionId();
      request.getSession().invalidate();
      ///// debugging
      {
      HttpSession sess = request.getSession(true);
      System.out.println("*******DEBUG*******");
      System.out.println("After change session ID");
      System.out.println("Request Session ID: " + request.getRequestedSessionId());
      System.out.println("        Session ID: " + sess.getId());
      List names = Collections.list(sess.getAttributeNames());
      for (Object n:names) {
        System.out.println("Session: " + n + " => " + sess.getAttribute((String)n));
      }
      System.out.println("**** END DEBUG ****");
      }
      ///// end
      HttpSession session = request.getSession();
      model.addAttribute("logininfo", new LoginInfo());
      model.addAttribute("msg", "You have been logout");
      return "redirect";
    }
}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
