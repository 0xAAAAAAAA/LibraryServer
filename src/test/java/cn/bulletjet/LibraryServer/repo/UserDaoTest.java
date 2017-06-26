package cn.bulletjet.LibraryServer.repo;

import static org.junit.Assert.assertEquals;

import cn.bulletjet.LibraryServer.entity.User;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试UserDao
 *
 * @author Bullet
 * @time 2017-06-09 16:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

  @Autowired
  private UserDao userDao;

  @Test
  public void testFindByUsername() {
    Optional<User> userOpt = userDao.findByUsername("王子杰");
    assertEquals(true, userOpt.isPresent());
    assertEquals("王子杰", userOpt.get().getUsername());
    assertEquals("123456", userOpt.get().getPassword());
  }

  @Test
  public void testExistsByUsername() {
    assertEquals(true, userDao.existsByUsername("王子杰"));
  }

  @Test
  public void testSave() {
    User user = new User();
    user.setUsername("杨海林");
    user.setPassword("123456");
    userDao.save(user);
    assertEquals(true, userDao.existsByUsername("杨海林"));
  }

}
