package cn.com.scitc.musicbox.dao;

import cn.com.scitc.musicbox.model.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;
@Repository
public interface MusicDao extends CrudRepository<Music,Integer> {
    //模糊查询
    List<Music> findByMusicnameLike(String musicname);
    List<Music> findBySingerLike(String musicname);
    //收藏
    @Query(value = "select distinct * from music,collection where music.musicname = collection.music_musicname and music.musicname=?",nativeQuery = true)
    public List<Music> findByMusicMusicname(@Param("musicname") String musicMusicname);
    //通过collection表中的username来查找music表里面对应的信息
    @Query(value = "select * from music,collection where music.musicname = collection.music_musicname and users_username = ?",nativeQuery = true)
    List<Music> findAllByUsername(@Param("users_username") String username);
    //通过music表中的musicname来获得music表中的所有信息
    List<Music> findAllByMusicname(@Param("musicname") String musicname);
    //声明事务性操作
    @Transactional
    //通知SpringDATA这是一个更新或者删除
    @Modifying
    @Query(value="UPDATE music SET leibie_leibiename = ? WHERE musicId = ?",nativeQuery = true)
    void update(@Param("leibie_leibiename") String leibie,@Param("musicId") Integer musicId);
    //删除歌曲
    public Music findByMusicname(String musicname);
    @Query(value = " select * from music where leibie_leibiename = ?",nativeQuery = true)
    List<Music> findByLeibiename(@Param("leibie_leibiename") String leibiename);
    @Query(value = "select * from music,leibie where music.leibie_leibiename = leibie.leibiename and leibie.leibieId = ?",nativeQuery = true)
    List<Music> findAllByLeibieId(@Param("leibieId") Integer leibieId);
}
