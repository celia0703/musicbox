package cn.com.scitc.musicbox.model;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
//public class Leibie implements Serializable {
    public class Leibie{
    private int leibieId;
    private String leibiename;
    private String leibieimg;
    @Id
    @Column(name = "leibieId", nullable = false)
    public int getLeibieId() {
        return leibieId;
    }

    public void setLeibieId(int leibieId) {
        this.leibieId = leibieId;
    }

    @Basic
    @Column(name = "leibiename", nullable = true, length = 255)
    public String getLeibiename() {
        return leibiename;
    }

    public void setLeibiename(String leibiename) {
        this.leibiename = leibiename;
    }

    public String getLeibieimg() {
        return leibieimg;
    }

    public void setLeibieimg(String leibieimg) {
        this.leibieimg = leibieimg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leibie leibie = (Leibie) o;
        return leibieId == leibie.leibieId &&
                Objects.equals(leibiename, leibie.leibiename);
    }

    @Override
    public String toString() {
        return "Leibie{" +
                "leibieId=" + leibieId +
                ", leibiename='" + leibiename + '\'' +
                ", leibieimg='" + leibieimg + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(leibieId, leibiename);
    }

}
