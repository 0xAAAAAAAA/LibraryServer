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
 * 用于管理员的操作界面
 *
 * @author Bullet
 * @time 2017-06-23 0:43
 */
@Controller
@RequestMapping("/view")
public class AdminController {

  @Autowired
  private UserService userService;
  @Autowired
  private BookService bookService;
  @Autowired
  private CheckoutService checkoutService;

  @RequestMapping("/book_center_admin.html")
  public String toBookCenter(
      @RequestParam(value = "bookname", required = false, defaultValue = "") String bookname,
      @RequestParam(value = "author", required = false, defaultValue = "") String author,
      Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    if (bookname.equals("") && author.equals("")) {
      model.addAttribute("search_condition", "所有书籍");
      model.addAttribute("list", bookService.findAllBooks());
      return "book_center_admin";
    } else if (bookname.equals("") && !author.equals("")) {
      model.addAttribute("search_condition", "作者为 " + author);
      model.addAttribute("list", bookService.findBooksByAuthor(author));
      return "book_center_admin";
    } else if (!bookname.equals("") && author.equals("")) {
      model.addAttribute("search_condition", "书名为 " + bookname);
      model.addAttribute("list", bookService.findBooksByName(bookname));
      return "book_center_admin";
    } else if (!bookname.equals("") && !author.equals("")) {
      model.addAttribute("search_condition", "书名为 " + bookname + "  AND  " + "作者为 " + author);
      model.addAttribute("list", bookService.findBooksByBookNameAndAuthor(bookname, author));
      return "book_center_admin";
    }

    // 其他所有未知情况返回全部书籍
    model.addAttribute("search_condition", "所有书籍");
    model.addAttribute("list", bookService.findAllBooks());
    return "book_center_admin";
  }

  @RequestMapping("/user_center_admin.html")
  public String toUserCenter(
      @RequestParam(value = "username", required = false, defaultValue = "") String username,
      Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    if (username.equals("")) {
      model.addAttribute("list", userService.findAllUser());
      model.addAttribute("search_condition", "全部用户");
      return "user_center_admin";
    } else {
      List<User> users = new ArrayList<User>();
      users.add(userService.findUserByUsername(username));
      model.addAttribute("search_condition", "用户名 为 " + username);
      model.addAttribute("list", users);
      return "user_center_admin";
    }
  }

  @RequestMapping("/checkout_center_admin.html")
  public String toCheckoutCenter(
      @RequestParam(value = "username", required = false, defaultValue = "") String username,
      HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    if (username.equals("")) {
      model.addAttribute("search_condition", "所有租借");
      List<Checkout> checkouts = checkoutService.findAll();
      List<Integer> bookIds = new ArrayList<>();
      for (Checkout checkout : checkouts) {
        bookIds.add(checkout.getBookId());
      }
      List<Book> books = bookService.findBooksByIds(bookIds);
      List<Integer> userIds = new ArrayList<>();
      for (Checkout checkout : checkouts) {
        userIds.add(checkout.getUserId());
      }
      List<User> users = userService.findUsersByIds(userIds);
      model.addAttribute("checkout_list", checkouts);
      model.addAttribute("book_list", books);
      model.addAttribute("user_list", users);
      return "checkout_center_admin";
    } else {
      model.addAttribute("search_condition", "用户名为 " + username);
      List<Checkout> checkouts = checkoutService.findCheckoutByUsername(username);
      List<Integer> bookIds = new ArrayList<>();
      for (Checkout checkout : checkouts) {
        bookIds.add(checkout.getBookId());
      }
      List<Book> books = bookService.findBooksByIds(bookIds);
      List<Integer> userIds = new ArrayList<>();
      for (Checkout checkout : checkouts) {
        userIds.add(checkout.getUserId());
      }
      List<User> users = userService.findUsersByIds(userIds);
      model.addAttribute("checkout_list", checkouts);
      model.addAttribute("book_list", books);
      model.addAttribute("user_list", users);
      return "checkout_center_admin";
    }

  }

  @RequestMapping("/add_book_admin.html")
  public String toAddBook(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    return "add_book_admin";
  }

  @RequestMapping("/add_book_submit.do")
  public String toAddBookSubmit(@RequestParam("bookname") String bookname,
      @RequestParam("author") String author,
      Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    Book book = new Book();
    book.setBookname(bookname);
    book.setAuthor(author);
    if (bookService.add(book) == null) {
      model.addAttribute("msg", "添加失败，请重试！");
      return "error";
    } else {
      return "redirect:/view/book_center_admin.html";
    }
  }

  @RequestMapping("/change_book_info.html")
  public String toChangeBookInfo(@RequestParam("bookId") int bookId, Model model,
      HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    Book book = bookService.findBookById(bookId);
    if (book == null) {
      model.addAttribute("msg", "找不到该书");
      return "error";
    } else {
      model.addAttribute("book", book);
      return "change_book_info";
    }
  }

  @RequestMapping("/change_book_submit.do")
  public String toChangeBookSubmit(@RequestParam("bookname") String bookname,
      @RequestParam("author") String author,
      @RequestParam("bookId") int bookId, Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    Book book = new Book();
    book.setId(bookId);
    book.setBookname(bookname);
    book.setAuthor(author);
    if (bookService.update(book) == null) {
      model.addAttribute("msg", "修改失败，请重试！");
      return "error";
    } else {
      return "redirect:/view/book_center_admin.html";
    }
  }

  @RequestMapping("delete_book_submit.do")
  public String toDeleteBookSubmit(@RequestParam("bookId") int bookId, Model model,
      HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
      model.addAttribute("msg", "请先登录!");
      return "login";
    }
    if (!user.isAdmin()) {
      model.addAttribute("msg", "权限不足!");
      return "error";
    }
    Book book = bookService.findBookById(bookId);
    if (book == null) {
      model.addAttribute("msg", "找不到该书");
      return "error";
    } else {
      bookService.deleteByBookId(bookId);
      return "redirect:/view/book_center_admin.html";
    }
  }


}
