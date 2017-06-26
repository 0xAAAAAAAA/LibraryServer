package cn.bulletjet.LibraryServer.service;

import cn.bulletjet.LibraryServer.entity.User;
import java.util.List;

/**
 * user的业务逻辑层
 *
 * @author Bullet
 * @time 2017-06-09 16:26
 */
public interface UserService {

  public User register(String username, String password);

  public User login(String username, String password);

  public boolean update(String username, String password, int id);

  public List<User> findAllUser();

  public User findUserByUsername(String username);

  public User findUserById(int userid);

  List<User> findUsersByIds(List<Integer> userIds);
}
