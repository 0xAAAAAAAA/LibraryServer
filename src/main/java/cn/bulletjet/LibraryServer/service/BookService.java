package cn.bulletjet.LibraryServer.service;

import cn.bulletjet.LibraryServer.entity.Book;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * 书籍信息操作的接口
 *
 * @author Bullet
 * @time 2017-06-10 15:06
 */
public interface BookService {

  public List<Book> findBooksByAuthor(String author);
  public List<Book> findBooksByName(String name);
  public Book findBookById(int id);
  public Book add(Book book);
  public List<Book> add(List<Book> books);
  @Transactional
  public void deleteByBookId(int id);
  public List<Book> findBooksByBookNameAndAuthor(String bookName, String author);
  public List<Book> findBooksByIds(List<Integer> ids);

  public List<Book> findAllBooks();

  public Book update(Book book);
}
