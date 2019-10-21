package cn.com.scitc.musicbox.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Music {
    private int musicId;
    private String musicname;
    private String singer;
    private String time;
    private String url;
    private String musicimg;
    private String lrc;
    private String leibieLeibiename;
    @Id
    @Column(name = "musicId", nullable = false)
    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    @Basic
    @Column(name = "musicname", nullable = false, length = 20)
    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    @Basic
    @Column(name = "singer", nullable = false, length = 20)
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 20)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "musicimg", nullable = true, length = 255)
    public String getMusicimg() {
        return musicimg;
    }

    public void setMusicimg(String musicimg) {
        this.musicimg = musicimg;
    }

    @Basic
    @Column(name = "lrc", nullable = true, length = 255)
    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }
    @Basic
    @Column(name = "leibie_leibiename", nullable = true, length = 20)
    public String getLeibieLeibiename() {
        return leibieLeibiename;
    }

    public void setLeibieLeibiename(String leibieLeibiename) {
        this.leibieLeibiename = leibieLeibiename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return musicId == music.musicId &&
                Objects.equals(musicname, music.musicname) &&
                Objects.equals(singer, music.singer) &&
                Objects.equals(time, music.time) &&
                Objects.equals(url, music.url) &&
                Objects.equals(musicimg, music.musicimg) &&
                Objects.equals(lrc, music.lrc);
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", musicname='" + musicname + '\'' +
                ", singer='" + singer + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", musicimg='" + musicimg + '\'' +
                ", lrc='" + lrc + '\'' +
                ", leibieLeibiename='" + leibieLeibiename + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicId, musicname, singer, time, url, musicimg, lrc);
    }
}
