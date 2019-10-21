package cn.com.scitc.musicbox.dao;

import cn.com.scitc.musicbox.model.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionDao extends CrudRepository<Collection,Integer>{
//  @Query(value = "insert into collection,music(collection.music_musicname) values(?) where collection.music_musicname=music.musicname",nativeQuery = true)
//    void saveMusicname(@Param("music_musicname") String musicname);
    //select * from music,collection where music.musicname = collection.music_musicname and collection.music_musicname
//    List<Collection> findAll();
    @Query(value = "select * from music,collection where music.musicname = collection.music_musicname and collection.users_username=?",nativeQuery = true)
    public List<Collection> findByUsersUsername(@Param("users_username") String username);
    @Query(value="select * from collection where music_musicname=? and users_username=?",nativeQuery = true)
    List<Collection> findAllByMusicMusicnameAndUsersUsername(@Param("music_musicname") String name,@Param("users_username") String username);
}
