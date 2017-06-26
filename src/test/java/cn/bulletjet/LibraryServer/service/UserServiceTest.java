package cn.bulletjet.LibraryServer.service;

import static org.junit.Assert.assertEquals;

import cn.bulletjet.LibraryServer.repo.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用于测试UserServiceImpl类
 *
 * @author Bullet
 * @time 2017-06-10 16:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

  @Autowired
  private UserService userService;

  // 数据访问层应该先于服务层被测试
  @Autowired
  private UserDao userDao;

  @Test
  public void testLogin() {
    assertEquals(true, userService.login("王子杰", "123456"));
  }

  @Test
  public void testRegister() {
    String username = "杨海林";
    String password = "456789";
    boolean beforeRegister = userDao.existsByUsername("杨海林");
    userService.register(username, password);
    boolean afterRegister = userDao.existsByUsername("杨海林");
    assertEquals(true, !beforeRegister && afterRegister);
  }

}
