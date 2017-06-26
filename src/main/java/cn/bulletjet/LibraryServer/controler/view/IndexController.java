package cn.bulletjet.LibraryServer.controler.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于首页
 *
 * @author Bullet
 * @time 2017-06-23 8:52
 */
@Controller
public class IndexController {

  @RequestMapping(value = {"/login.html", "/"})
  public String toIndex() {
    return "redirect:/view/login.html";
  }

}
