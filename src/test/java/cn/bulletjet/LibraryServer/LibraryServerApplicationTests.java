package cn.bulletjet.LibraryServer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试图书馆入口类
 *
 * @author Bullet
 * @time 2017-06-09 1:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LibraryServerApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void contextLoads() {

  }

}
