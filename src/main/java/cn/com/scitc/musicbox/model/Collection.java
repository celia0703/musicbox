package cn.com.scitc.musicbox.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Collection {
    private int collectionId;
    private String musicMusicname;
    private String usersUsername;

    @Id
    @Column(name = "collectionId", nullable = false)
    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
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
    @Column(name = "users_username", nullable = false, length = 20)
    public String getUsersUsername() {
        return usersUsername;
    }

    public void setUsersUsername(String usersUsername) {
        this.usersUsername = usersUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return collectionId == that.collectionId &&
                Objects.equals(musicMusicname, that.musicMusicname) &&
                Objects.equals(usersUsername, that.usersUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, musicMusicname, usersUsername);
    }
}
