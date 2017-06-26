package cn.bulletjet.LibraryServer.controler.view;

import cn.bulletjet.LibraryServer.entity.User;
import cn.bulletjet.LibraryServer.service.BookService;
import cn.bulletjet.LibraryServer.service.CheckoutService;
import cn.bulletjet.LibraryServer.service.UserService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 公用的界面
 *
 * @author Bullet
 * @time 2017-06-23 0:43
 */
@Controller
@RequestMapping("/view")
public class CommonController {

  @Autowired
  private UserService userService;

  @Autowired
  private BookService bookService;

  @Autowired
  private CheckoutService checkoutService;

  @RequestMapping(path = {"/login.html", "/index.html"})
  public String toLogin() {
    return "login";
  }

  @RequestMapping("/doLoginOrRegister.html")
  public String toDoLogin(@RequestParam String username, @RequestParam String password,
      @RequestParam String LoginOrRegister, HttpSession session, Model model) {
    Optional<Boolean> isLoginOpt;
    // 检查操作参数，判定是否合法，不合法跳转到error界面，合法则判定是登录还是注册
    if (LoginOrRegister.equals("登录")) {
      isLoginOpt = Optional.of(true);
    } else if (LoginOrRegister.equals("注册")) {
      isLoginOpt = Optional.of(false);
    } else {
      model.addAttribute("msg", "非法的操作参数！");
      return "error";
    }
    // 操作参数为登录
    if (isLoginOpt.isPresent() && isLoginOpt.get() == true) {
      User user = userService.login(username, password);
      if (user == null) {
        model.addAttribute("msg", "用户名或者密码错误！");
        return "login";
      } else {
        session.setAttribute("user", user);
        if (!user.isAdmin()) {
          return "forward:book_center.html";
        } else {
          return "forward:book_center_admin.html";
        }
      }
    }
    // 操作参数为注册
    if (isLoginOpt.isPresent() && isLoginOpt.get() == false) {
      User user = userService.register(username, password);
      if (user == null) {
        model.addAttribute("msg", "注册失败！用户名重复！");
        return "login";
      } else {
        model.addAttribute("msg", "注册成功！请登录！");
        return "login";
      }
    }
    model.addAttribute("msg", "未知错误");
    return "error";
  }

  @RequestMapping("/change_user_info.html")
  public String toChangeUserInfo(
      @RequestParam(value = "username", required = false) String username, Model model,
      HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    } else {
      if (user.isAdmin()) {       // 管理员修改
        User opUser = userService.findUserByUsername(username);
        if (opUser == null) {
          model.addAttribute("msg", "该用户不存在!");
          return "error";
        } else {
          model.addAttribute("op_user", opUser);
          return "change_user_info";
        }
      } else {          // 用户自己修改
        model.addAttribute("op_user", user);
        return "change_user_info";
      }
    }
  }

  @RequestMapping("/change_user_submit.do")
  public String toChangeUserSubmit(@RequestParam("username") String username,
      @RequestParam("password") String password,
      @RequestParam("userid") String strUserid, Model model, HttpSession session) {
    int userid = Integer.decode(strUserid.replaceAll(",", ""));
    User user = (User) session.getAttribute("user");
    User opUser = userService.findUserById(userid);
    // 如果当前登录用户不是管理员且不是用户本人，不允许修改
    if (!user.isAdmin() && user.getId() != opUser.getId()) {
      model.addAttribute("msg", "权限不足！");
      return "error";
    }
    if (opUser == null) {
      model.addAttribute("msg", "用户不存在！");
      return "error";
    }

    if (userService.update(username, password, opUser.getId())) {
      if (!((User) session.getAttribute("user")).isAdmin()) {                                // 用户本人操作
        model.addAttribute("msg", "账户信息已修改 ，请重新登录");
        session.removeAttribute("user");
        return "login";
      } else {                                                                                      // 管理员操作
        return "/view/user_center_admin.html";
      }
    } else {
      model.addAttribute("用户信息更新失败");
      return "error";
    }
  }

}
