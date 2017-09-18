package my.mva;

import my.mva.libs.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.io.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LinkController {
  private ServletContext ctx;
    
  @GetMapping("/link")
  public String show(@RequestParam String mode, @RequestParam String target,
      HttpServletRequest request, Model model) {
    this.ctx = request.getServletContext();
    if ( mode.equals("file") ) showFile(target, model);
    if ( mode.equals("dir") ) showDir(target, model);
    model.addAttribute("mode", mode);
    model.addAttribute("target", target);
    return "link";
  }

  private void showFile(String target, Model model) {
    String content = "Nothing to see";
    try {
      String dir = ctx.getRealPath("/css/blog.css");
      String fn = dir + target;
      System.out.println("Reading :" + fn);
      content = FileUtils.readFileToString(new File(fn));
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    model.addAttribute("content", content);
  }

  private void showDir(String target, Model model) {

  }
}

// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
