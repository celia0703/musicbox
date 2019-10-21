package cn.com.scitc.musicbox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Comments {
    private int commentId;
    private String usersUsername;
    private String musicMusicname;
    private String text;
    private String time;

    @Id
    @Column(name = "commentId", nullable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Basic
    @Column(name = "users_username", nullable = false, length = 20)
    public String getUsersUsername() {
        return usersUsername;
    }

    public void setUsersUsername(String usersUsername) {
        this.usersUsername = usersUsername;
    }

    @Basic
    @Column(name = "music_musicname", nullable = false, length = 20)
    public String getMusicMusicname() {
        return musicMusicname;
    }

    public void setMusicMusicname(String musicMusicname) {
        this.musicMusicname = musicMusicname;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 255)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", usersUsername='" + usersUsername + '\'' +
                ", musicMusicname='" + musicMusicname + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return commentId == comments.commentId &&
                Objects.equals(usersUsername, comments.usersUsername) &&
                Objects.equals(musicMusicname, comments.musicMusicname) &&
                Objects.equals(text, comments.text) &&
                Objects.equals(time, comments.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, usersUsername, musicMusicname, text, time);
    }
}
