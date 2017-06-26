package cn.bulletjet.LibraryServer.repo;

import cn.bulletjet.LibraryServer.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

/**
 * 用于对user表进行操作的数据访问层
 *
 * @author Bullet
 * @time 2017-06-09 15:53
 */
@Repository
@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserDao extends CrudRepository<User, Integer> {
  public Optional<User> findByUsername(String username);
  public boolean existsByUsername(String username);
}
