package cn.bulletjet.LibraryServer.controler.api;

import cn.bulletjet.LibraryServer.entity.User;
import cn.bulletjet.LibraryServer.service.CheckoutService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用于租借的API控制器
 *
 * @author Bullet
 * @time 2017-06-20 15:14
 */
@Controller
@RequestMapping("/api/")
public class CheckoutApiController {

  @Autowired
  private CheckoutService checkoutService;

  @RequestMapping("/rent")
  public String toRent(@RequestParam("bookId") int bookId, HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (checkoutService.checkout(user.getId(), bookId) == null) {
      model.addAttribute("msg", "租借失败！");
      return "error";
    } else {
      model.addAttribute("msg", "租借成功！");
      return "success";
    }
  }

  @RequestMapping("/return")
  public String toReturn(@RequestParam("checkoutId") int checkoutId, HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!checkoutService.returnBook(checkoutId)) {
      model.addAttribute("msg", "还书失败！");
      return "error";
    } else {
      model.addAttribute("msg", "还书成功！");
      return "success";
    }
  }

}
