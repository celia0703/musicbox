package cn.com.scitc.musicbox.dao;

import cn.com.scitc.musicbox.model.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsDao extends CrudRepository<Comments,Integer> {
    @Query(value = "select * from users,comments where users.username = comments.users_username and users_username = ?",nativeQuery = true)
    List<Comments> findByUsersUsername(@Param("users_username") String username);
    @Query(value = "select * from comments",nativeQuery = true)
    Page<Comments> findPage(Pageable pageable);
}
