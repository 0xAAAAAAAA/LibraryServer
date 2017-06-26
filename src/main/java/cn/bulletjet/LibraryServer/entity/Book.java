package cn.bulletjet.LibraryServer.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用于表示书籍的实体类
 *
 * @author Bullet
 * @time 2017-06-09 15:11
 */
@Entity
public class Book implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String bookName;
  private String author;
  private boolean isBorrowed;

  public boolean isBorrowed() {
    return isBorrowed;
  }

  public void setBorrowed(boolean borrowed) {
    isBorrowed = borrowed;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookname(String bookname) {
    this.bookName = bookname;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
