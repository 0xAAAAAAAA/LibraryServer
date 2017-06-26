package cn.bulletjet.LibraryServer.service;

import cn.bulletjet.LibraryServer.entity.Checkout;
import java.util.List;

/**
 * 借书操作的接口
 *
 * @author Bullet
 * @time 2017-06-10 15:14
 */
public interface CheckoutService {

  public Checkout checkout(int userId, int bookId);
  public boolean returnBook(int id);
  public List<Checkout> findCheckoutsByUserId(int id);

  public List<Checkout> findAll();

  public List<Checkout> findCheckoutByUsername(String username);
}
