package cn.com.scitc.musicbox.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class Users implements Serializable {
    private int userId;
    private String username;
    private String userpwd;
    private String gender;
    private String age;
    private String number;
    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Basic
    @Column(name = "userpwd", nullable = false, length = 20)
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    @Basic
    @Column(name = "gender", nullable = true, length = 20)
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Basic
    @Column(name = "age", nullable = true, length = 20)
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    @Basic
    @Column(name = "number", nullable = true, length = 20)
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userpwd, gender, age, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId == users.userId &&
                Objects.equals(username, users.username) &&
                Objects.equals(userpwd, users.userpwd) &&
                Objects.equals(gender, users.gender) &&
                Objects.equals(age, users.age) &&
                Objects.equals(number, users.number);
    }
}
