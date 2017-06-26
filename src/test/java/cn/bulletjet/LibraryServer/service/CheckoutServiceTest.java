package cn.bulletjet.LibraryServer.service;

import static org.junit.Assert.assertEquals;

import cn.bulletjet.LibraryServer.entity.Checkout;
import cn.bulletjet.LibraryServer.repo.BookDao;
import cn.bulletjet.LibraryServer.repo.CheckoutDao;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试CheckoutServiceImpl类
 *
 * @author Bullet
 * @time 2017-06-13 11:36
 */

@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
// 这里使用了PROPAGATION_REQUIRED事务，不能使用事务修饰符修饰，否则会合并事务，无法测试,JPA无法使用嵌套事务，但JDBC可以
// 测试之后需要手动修改数据库记录(checkout.is_returned和checkout.real_return_time, book.is_borrowed)
public class CheckoutServiceTest {

  @Autowired
  private CheckoutService checkoutService;

  @Autowired
  private CheckoutDao checkoutDao;

  @Autowired
  private BookDao bookDao;

  @Test
  public void testReturnBook() {
    Optional<Checkout> checkoutOptBefore = checkoutDao.findById(5);
    assertEquals(false, checkoutOptBefore.get().isReturned());
    checkoutService.returnBook(5);
    Optional<Checkout> checkoutOpt = checkoutDao.findById(5);
    assertEquals(true, checkoutOpt.isPresent());
    assertEquals(true, checkoutOpt.get().isReturned());
    assertEquals(false, bookDao.findById(2).get().isBorrowed());
    System.out.println("Finiished!");
  }

  @Test
  public void testCheckout() {
    checkoutService.checkout(2014302634, 2);
  }




}
