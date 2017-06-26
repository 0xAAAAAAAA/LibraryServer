package cn.bulletjet.LibraryServer.controler.view;

import cn.bulletjet.LibraryServer.entity.Book;
import cn.bulletjet.LibraryServer.entity.Checkout;
import cn.bulletjet.LibraryServer.entity.User;
import cn.bulletjet.LibraryServer.service.BookService;
import cn.bulletjet.LibraryServer.service.CheckoutService;
import cn.bulletjet.LibraryServer.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 首页控制器
 *
 * @author Bullet
 * @time 2017-06-15 0:08
 */
@Controller
@RequestMapping("/view")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private BookService bookService;

  @Autowired
  private CheckoutService checkoutService;

  @RequestMapping("/book_center.html")
  public String toBookCenter(@RequestParam(value = "bookname", required = false, defaultValue = "") String bookname,
      @RequestParam(value = "author", required = false, defaultValue = "") String author, Model model) {
    if (bookname.equals("") && author.equals("")) {
      model.addAttribute("search_condition", "所有书籍");
      model.addAttribute("list", bookService.findAllBooks());
      return "book_center";
    } else if (bookname.equals("") && !author.equals("")) {
      model.addAttribute("search_condition", "作者为 " + author);
      model.addAttribute("list", bookService.findBooksByAuthor(author));
      return "book_center";
    } else if (!bookname.equals("") && author.equals("")){
      model.addAttribute("search_condition", "书名为 " + bookname);
      model.addAttribute("list", bookService.findBooksByName(bookname));
      return "book_center";
    } else if (!bookname.equals("") && !author.equals("")) {
      model.addAttribute("search_condition", "书名为 " + bookname + "  AND  " + "作者为 " + author);
      model.addAttribute("list", bookService.findBooksByBookNameAndAuthor(bookname, author));
      return "book_center";
    }

    // 其他所有未知情况返回全部书籍
    model.addAttribute("search_condition", "所有书籍");
    model.addAttribute("list", bookService.findAllBooks());
    return "book_center";
  }

  @RequestMapping("/user_center.html")
  public String toUserCenter(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    } else {
      return "user_center";
    }
  }

  @RequestMapping("/checkout_center.html")
  public String toCheckoutCenter(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    } else {
      List<Checkout> checkouts = checkoutService.findCheckoutsByUserId(user.getId());
      List<Integer> bookIds = new ArrayList<>();
      for (Checkout checkout: checkouts) {
        bookIds.add(checkout.getBookId());
      }
      List<Book> books = bookService.findBooksByIds(bookIds);
      model.addAttribute("checkout_list", checkouts);
      model.addAttribute("book_list", books);
      return "checkout_center";
    }
  }

}
