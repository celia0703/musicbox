package cn.com.scitc.musicbox.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Admin {
    private int adminId;
    private String admin;
    private String adminpwd;

    @Id
    @Column(name = "adminId", nullable = false)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "admin", nullable = true, length = 20)
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "adminpwd", nullable = true, length = 20)
    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin1 = (Admin) o;
        return adminId == admin1.adminId &&
                Objects.equals(admin, admin1.admin) &&
                Objects.equals(adminpwd, admin1.adminpwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, admin, adminpwd);
    }
}
