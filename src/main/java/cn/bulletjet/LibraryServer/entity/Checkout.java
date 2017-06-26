package cn.bulletjet.LibraryServer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 表示借阅的实体类
 *
 * @author Bullet
 * @time 2017-06-09 15:12
 */
@Entity
public class Checkout implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int userId;
  private int bookId;
  private Date checkoutTime;
  private Date shouldReturnTime;
  private Date realReturnTime;
  private boolean isReturned;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public Date getCheckoutTime() {
    return checkoutTime;
  }

  public void setCheckoutTime(Date checkoutTime) {
    this.checkoutTime = checkoutTime;
  }

  public Date getShouldReturnTime() {
    return shouldReturnTime;
  }

  public void setShouldReturnTime(Date shouldReturnTime) {
    this.shouldReturnTime = shouldReturnTime;
  }

  public Date getRealReturnTime() {
    return realReturnTime;
  }

  public void setRealReturnTime(Date realReturnTime) {
    this.realReturnTime = realReturnTime;
  }

  public boolean isReturned() {
    return isReturned;
  }

  public void setReturned(boolean returned) {
    isReturned = returned;
  }
}
