package cn.com.scitc.musicbox.dao;

import cn.com.scitc.musicbox.model.Leibie;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeibieDao extends CrudRepository<Leibie,Integer> {
//    @Query(value = "select leibiename from leibie where leibieId=?",nativeQuery = true)
//    Leibie findByleibieIdList(@Param("leibieId") Integer leibieId);
    Leibie findByLeibiename(@Param("leibiename") String name);
}