package cn.bulletjet.LibraryServer.service;

import cn.bulletjet.LibraryServer.entity.Book;
import cn.bulletjet.LibraryServer.entity.Checkout;
import cn.bulletjet.LibraryServer.entity.User;
import cn.bulletjet.LibraryServer.repo.BookDao;
import cn.bulletjet.LibraryServer.repo.CheckoutDao;
import cn.bulletjet.LibraryServer.repo.UserDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 借书操作接口的实现类
 *
 * @author Bullet
 * @time 2017-06-10 15:15
 */
@Service("checkoutService")
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

  @Autowired
  private CheckoutDao checkoutDao;

  @Autowired
  private UserDao userDao;

  @Autowired
  private BookDao bookDao;

  @Override
  public Checkout checkout(int userId,int bookId) {

    Optional<Book> bookOpt = bookDao.findById(bookId);          // 该bookID表示的书籍须存在，且未被借出
    if (!bookOpt.isPresent() || bookOpt.get().isBorrowed() == true) {
      return null;
    }
    Optional<User> userOpt = userDao.findById(userId);          // 该userID表示的用户须存在
    if (!userOpt.isPresent()) {
      return null;
    }
    Checkout checkout = new Checkout();
    checkout.setBookId(bookId);
    checkout.setUserId(userId);
    Date currentTime = new Date();
    checkout.setCheckoutTime(currentTime);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentTime);
    calendar.add(Calendar.MONTH, 3);
    checkout.setShouldReturnTime(calendar.getTime());
    checkout.setReturned(false);
    bookDao.changeStatus(bookId, true);
    return checkoutDao.save(checkout);
  }

  @Override
  public boolean returnBook(int id) {
    checkoutDao.changeStatusToReturned(id);
    bookDao.changeStatus(checkoutDao.findById(id).get().getBookId(), false);
    return true;
  }

  @Override
  public List<Checkout> findCheckoutsByUserId(int id) {
    return checkoutDao.findCheckoutsByUserId(id);
  }

  @Override
  public List<Checkout> findAll() {
    List<Checkout> list = new ArrayList<>();
    Iterable<Checkout> checkoutsIt = checkoutDao.findAll();
    checkoutsIt.forEach((Checkout c) -> list.add(c));
    return list;
  }

  @Override
  public List<Checkout> findCheckoutByUsername(String username) {
    List<Checkout> list = new ArrayList<>();
    Iterable<Checkout> checkoutsIt = checkoutDao.findCheckoutsByUsername(username);
    checkoutsIt.forEach((Checkout c) -> list.add(c));
    return list;
  }
}
