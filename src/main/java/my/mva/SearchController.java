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
public class SearchController {

    @GetMapping("/search")
    public String postForm(@RequestParam String q, Model model) {
      model.addAttribute("keyword", q);
      model.addAttribute("mark", "<mark>"+q+"mark");
      model.addAttribute("found", 0);
      return "search";
    }
}
// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
