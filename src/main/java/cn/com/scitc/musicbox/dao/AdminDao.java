package cn.com.scitc.musicbox.dao;

import cn.com.scitc.musicbox.model.Admin;
import org.springframework.data.repository.CrudRepository;
public interface AdminDao extends CrudRepository<Admin,Integer> {
    public Admin findByAdmin(String admin);
}
