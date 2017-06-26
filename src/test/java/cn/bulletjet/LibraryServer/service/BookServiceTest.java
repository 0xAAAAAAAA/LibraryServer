package cn.bulletjet.LibraryServer.service;

import static org.junit.Assert.assertEquals;

import cn.bulletjet.LibraryServer.entity.Book;
import cn.bulletjet.LibraryServer.repo.BookDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试BookServiceImpl类
 *
 * @author Bullet
 * @time 2017-06-13 9:49
 */

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookDao bookDao;

  @Test
  public void testFindBooksByName() {
    List<Book> list =  bookService.findBooksByName("现代操作系统");
    assertEquals(1, list.size());
    assertEquals(2, list.get(0).getId());
  }

  @Test
  public void testFindBooksByAuthor() {
    List<Book> list = bookService.findBooksByAuthor("Andrew");
    assertEquals(1, list.size());
    assertEquals(2, list.get(0).getId());
  }

  @Test
  public void testFindBooksById() {
    assertEquals("现代操作系统", bookService.findBookById(2).getBookName());
  }

  @Test
  public void testAdd() {
    Book book = new Book();
    book.setBookname("梅贾的窃魂券");
    book.setAuthor("Miki");
    bookService.add(book);
    assertEquals("Miki", bookDao.findByBookName("梅贾的窃魂券").get(0).getAuthor());
  }

  @Test
  public void testAddByList() {
    Book book1 = new Book();
    book1.setBookname("梅贾的窃魂券");
    book1.setAuthor("Miki");
    Book book2 = new Book();
    book2.setBookname("梅贾的窃魂券");
    book2.setAuthor("Mikia");
    List<Book> books = new ArrayList<>();
    books.add(book1);
    books.add(book2);
    bookService.add(books);
    assertEquals(2, bookDao.findByBookName("梅贾的窃魂券").size());
  }

  @Test
  public void testDelete() {
    bookService.delete(2);
    assertEquals(false, bookDao.findById(2).isPresent());
  }

}
