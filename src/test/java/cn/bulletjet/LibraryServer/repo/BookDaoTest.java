package cn.bulletjet.LibraryServer.repo;

import static org.junit.Assert.assertEquals;

import cn.bulletjet.LibraryServer.entity.Book;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试BookDao类
 *
 * @author Bullet
 * @time 2017-06-11 22:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookDaoTest {

  @Autowired
  private BookDao bookDao;

  @Test
  public void testFindByBookName() {
    List<Book> books = new ArrayList<>();
    Book book = new Book();
    book.setId(2);
    book.setAuthor("Andrew");
    book.setBookname("现代操作系统");
    book.setBorrowed(true);
    books.add(book);
    assertEquals(books.get(0).getBookName(), bookDao.findByBookName("现代操作系统").get(0).getBookName());
  }


  @Test
  public void testFindByAuthor() {
    List<Book> books = new ArrayList<>();
    Book book = new Book();
    book.setId(2);
    book.setAuthor("Andrew");
    book.setBookname("现代操作系统");
    book.setBorrowed(true);
    books.add(book);
    assertEquals(books.get(0).getAuthor(), bookDao.findByAuthor("Andrew").get(0).getAuthor());
  }

  @Test
  public void testFindById() {
    assertEquals(2, bookDao.findById(2).get().getId());
  }

  @Test
  public void testSave() {
    Book book = new Book();
    book.setBorrowed(false);
    book.setAuthor("Stevens");
    book.setBookname("UNIX 网络编程");
    bookDao.save(book);
    assertEquals(book.getBookName(), bookDao.findByBookName("UNIX 网络编程").get(0).getBookName());
  }

  @Test
  public void testChangeStatus() {
    bookDao.changeStatus(2, false);
    assertEquals(false, bookDao.findById(2).get().isBorrowed());
  }

}
