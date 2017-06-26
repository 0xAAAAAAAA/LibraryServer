package cn.bulletjet.LibraryServer.repo;

import cn.bulletjet.LibraryServer.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Book表的DAO
 *
 * @author Bullet
 * @time 2017-06-10 15:03
 */
@Repository
@RepositoryDefinition(domainClass = Book.class, idClass = Integer.class)
public interface BookDao extends CrudRepository<Book, Integer> {

  public List<Book> findByAuthor(String author);
  public List<Book> findByBookName(String bookName);
  public List<Book> findBooksByBookNameAndAuthor(String bookName, String author);

  @Modifying
  @Transactional
  @Query("update Book b set b.isBorrowed = :status where b.id = :id")
  public int changeStatus(@Param("id") int id, @Param("status") boolean isBorrowedWillBe);

}
