package cn.com.scitc.musicbox;

import cn.com.scitc.musicbox.dao.*;
import cn.com.scitc.musicbox.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.StyleSheet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicboxApplicationTests {

    @Test
    public void contextLoads() {

    }
    @Autowired private LeibieDao leibieDao;
    @Autowired private MusicDao musicDao;
    @Autowired private CollectionDao collectionDao;
    @Autowired private UsersDao usersDao;
    @Autowired private CommentsDao commentsDao;
    @Test
    public void test01(){
        Optional<Leibie> opt = leibieDao.findById(1);
        Leibie leibie1=opt.get();
        if(opt.isPresent())
        {
            System.out.println("有结果");
            System.out.println(opt.get());
            System.out.println(leibie1);
        }
    }
    @Test
    public void test02(){
//        List<Music> list = leibieDao.findById(1).get().getMusicList();
//        System.out.println(list.get(0).getLeibie().getLeibiename());
    }
    @Test
    public void test03(){
//        List<Music> list = leibieDao.findById(1).get().getMusicList();
    }
    @Test
    public void test04(){
//        List<Collection> byCollection = collectionDao.findByMusicMusicname("Taylor Swift-Lover");
//        List<Music> byMusicMusicname = musicDao.findByMusicMusicname("Taylor Swift-Lover");
//       CollectionContainsMusic containsMusic = new CollectionContainsMusic();
//       containsMusic.setMusic(byMusicMusicname);
//       containsMusic.setCollection(byCollection);
//        for (Music music : containsMusic.getMusic()) {
//            System.out.println(music.toString());
//        }
    }
    @Test
    public void  test05(){
//        List<Collection> byMusicMusicname = collectionDao.findByMusicMusicname("Safe&Sounds");
//
//        System.out.println(byMusicMusicname);
    }
    @Test
    public void test06() {
        String username = "苏汉伟";
        String musicname="Taylor Swift-Lover";
        List<Collection> collection1 = collectionDao.findAllByMusicMusicnameAndUsersUsername(musicname,username);
        if (username != null) {
            if (collection1.size() == 0) {
                System.out.println(1);
            }else if(collection1.equals(username)==false){
                System.out.println(2);
            }else if (collection1.equals(username)){
                System.out.println(3);
            }
        }
    }
    @Test
    public  void test09(){
        String musicname="Taylor Swift-Lover";
        List<Collection> collection1 = collectionDao.findAllByMusicMusicnameAndUsersUsername(musicname,"苏汉伟");
        if (collection1.size() == 0){
            System.out.println("null");
        }else{
            System.out.println("1");
        }
    }
    @Test
    public void test07(){
        Iterable<Music> listmu = musicDao.findAll();
        System.out.println(listmu);
    }
    @Test
    public void test08(){
        List<Music> musics = musicDao.findAllByUsername("苏汉伟");
        System.out.println(musics);
    }
    @Test
    public void test10(){
//        List<Music> list = musicDao.findAllByMusicname("wildest");
//        for(Music musics : list){
//            System.out.println(musics.getLeibie().getLeibiename());
//        }
    }
    @Test
    public void test11(){
//        List<Music> list1 = leibieDao.findById(1).get().getMusicList();
        Leibie lover = leibieDao.findByLeibiename("lover");
        System.out.println(lover);
    }
    @Test
    public void test12(){
      List<Music> musicList = musicDao.findAllByUsername("zhangsan");
        System.out.println(musicList);
        List<Music> list1 = musicDao.findByLeibiename("lover");
        System.out.println(list1);
    }
    @Test
    public  void test13(){
        Optional<Leibie> leibie = leibieDao.findById(1);
        Leibie leibie1=leibie.get();
        List<Music> musicList = musicDao.findAllByLeibieId(1);
        System.out.println(leibie1);
        System.out.println(musicList);
    }
    @Test
    public void test14(){
        Iterable<Leibie> leibielist = leibieDao.findAll();
        Iterable<Music> listmu = musicDao.findAll();
        List list=new ArrayList();
        for (Music music : listmu){
            list.add(music);
        }
        System.out.println(leibielist);
    }
}
