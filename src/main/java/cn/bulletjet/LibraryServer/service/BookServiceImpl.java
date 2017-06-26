package cn.bulletjet.LibraryServer.service;

import cn.bulletjet.LibraryServer.entity.Book;
import cn.bulletjet.LibraryServer.repo.BookDao;
import cn.bulletjet.LibraryServer.repo.CheckoutDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 书籍信息操作接口的实现类
 *
 * @author Bullet
 * @time 2017-06-10 15:08
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

  @Autowired
  private BookDao bookDao;

  @Autowired
  private CheckoutDao checkoutDao;

  @Override
  public List<Book> findBooksByAuthor(String author) {
    return bookDao.findByAuthor(author);
  }

  @Override
  public List<Book> findBooksByName(String name) {
    return bookDao.findByBookName(name);
  }

  @Override
  public Book findBookById(int id) {
    Optional<Book> bookOpt = bookDao.findById(id);
    if (bookOpt.isPresent()) {
      return bookOpt.get();
    }
    return null;
  }

  @Override
  public Book add(Book book) {
    return bookDao.save(book);
  }

  @Override
  public List<Book> add(List<Book> books) {
    Iterable<Book> bookIterable = bookDao.saveAll(books);
    List<Book> bookRet = new ArrayList<>();
    bookIterable.forEach(((Book b) -> bookRet.add(b)));
    return bookRet;
  }

  @Override
  @Transactional
  public void deleteByBookId(int id) {
    if (checkoutDao.findCheckoutsByBookId(id) != null) {
      checkoutDao.deleteByBookId(id);
    }
    bookDao.deleteById(id);
  }

  @Override
  public List<Book> findBooksByBookNameAndAuthor(String bookName, String author) {
    return bookDao.findBooksByBookNameAndAuthor(bookName, author);
  }

  @Override
  public List<Book> findBooksByIds(List<Integer> ids) {
    List<Book> bookRet = new ArrayList<>();
    for (int id : ids) {
      bookRet.add(bookDao.findById(id).get());
    }
    return bookRet;
  }

  @Override
  public List<Book> findAllBooks() {
    List<Book> bookRet = new ArrayList<>();
    bookDao.findAll().forEach(((Book b) -> bookRet.add(b)));
    return bookRet;
  }

  @Override
  public Book update(Book book) {
    return bookDao.save(book);
  }
}
