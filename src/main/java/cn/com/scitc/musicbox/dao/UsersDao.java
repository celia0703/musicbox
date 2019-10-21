package cn.com.scitc.musicbox.dao;
import cn.com.scitc.musicbox.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface UsersDao extends CrudRepository<Users,Integer> {
   public Users findByUsername(String username);
//   List<Users>findByUserpwd(String userpwd);
}
