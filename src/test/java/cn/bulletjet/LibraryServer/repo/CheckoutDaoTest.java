package cn.bulletjet.LibraryServer.repo;

import static org.junit.Assert.assertEquals;

import cn.bulletjet.LibraryServer.entity.Checkout;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试CheckoutDao类
 *
 * @author Bullet
 * @time 2017-06-13 10:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
// Mysql不支持嵌套事务，开启第二个事务的时候会把第一个事务提交, 每次测试完要手动修改数据库（checkout.is_returned和checkout.real_return_time）
public class CheckoutDaoTest {

  @Autowired
  private CheckoutDao checkoutDao;

  @Autowired
  private BookDao bookDao;

  @Test
  public void testChangeStatusToReturned() {

    Optional<Checkout> checkoutBefore = checkoutDao.findById(5);
    assertEquals(false, checkoutBefore.get().isReturned());
    checkoutDao.changeStatusToReturned(5);
    Optional<Checkout> checkoutOpt = checkoutDao.findById(5);
    assertEquals(true, checkoutOpt.isPresent());
    assertEquals(true, checkoutOpt.get().isReturned());
  }


}
