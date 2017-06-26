package cn.bulletjet.LibraryServer.repo;

import cn.bulletjet.LibraryServer.entity.Checkout;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Checkout表的DAO
 *
 * @author Bullet
 * @time 2017-06-10 15:02
 */
@Repository
@RepositoryDefinition(domainClass = Checkout.class, idClass = Integer.class)
public interface CheckoutDao extends CrudRepository<Checkout, Integer> {

  @Modifying
  @Transactional
  @Query("update Checkout a set a.isReturned = true, a.realReturnTime = current_time where a.id = :id")
  public void changeStatusToReturned(@Param("id") int id);

  public List<Checkout> findCheckoutsByUserId(int id);

  @Query(value = "select c from Checkout c where c.userId in (select u.id from User u where u.username = :username)")
  public Iterable<Checkout> findCheckoutsByUsername(@Param("username") String username);

  public Checkout findCheckoutsByBookId(int bookId);

  public void deleteByBookId(int bookId);


}
