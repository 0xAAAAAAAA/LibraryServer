package cn.bulletjet.LibraryServer.service;

import cn.bulletjet.LibraryServer.entity.User;
import cn.bulletjet.LibraryServer.repo.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService的实现类
 *
 * @author Bullet
 * @time 2017-06-09 16:41
 */
@Service("userService")
public class UserServicesImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User register(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    if(!userDao.existsByUsername(username)) {
      return userDao.save(user);
    }
    return null;
  }

  @Override
  public User login(String username, String password) {
    Optional<User> userOpt = userDao.findByUsername(username);
    if (userOpt.isPresent() && userOpt.get().getUsername().equals(username) && userOpt.get().getPassword().equals(password))
      return userOpt.get();
    return null;
  }

  @Override
  public boolean update(String username, String password, int id) {
    Optional<User> userOpt = userDao.findById(id);
    if (userOpt != null && userOpt.isPresent()) {
      User user = userOpt.get();
      user.setUsername(username);
      user.setPassword(password);
      return (userDao.save(user) != null);
    } else {
      return false;
    }
  }

  @Override
  public List<User> findAllUser() {
    List<User> users = new ArrayList<>();
    Iterable<User> userIt = userDao.findAll();
    userIt.forEach((User user) -> users.add(user));
    return users;
  }

  @Override
  public User findUserByUsername(String username) {
    Optional<User> userOpt = userDao.findByUsername(username);
    return userOpt.isPresent() ? userOpt.get() : null;
  }

  @Override
  public User findUserById(int userid) {
    Optional<User> userOpt = userDao.findById(userid);
    return userOpt.isPresent() ? userOpt.get() : null;
  }

  @Override
  public List<User> findUsersByIds(List<Integer> ids) {
    List<User> userRet = new ArrayList<>();
    for (int id : ids) {
      userRet.add(userDao.findById(id).get());
    }
    return userRet;
  }
}
