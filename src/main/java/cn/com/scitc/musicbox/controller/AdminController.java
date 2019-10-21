package cn.com.scitc.musicbox.controller;

import cn.com.scitc.musicbox.dao.*;
import cn.com.scitc.musicbox.model.*;
import cn.com.scitc.musicbox.myconfig.WebMvcConfig;
import com.sun.javafx.collections.MappingChange;
import javafx.application.Application;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private MusicDao musicDao;
    @Autowired
    private LeibieDao leibieDao;
    @Autowired
    private CommentsDao commentsDao;
    @GetMapping("/login")
    private String login(){
        return "admin/login";
    }
    @GetMapping("/index")
    private String index(){
        return "admin/index";
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //登录
    @PostMapping("/ulogin")
    private String ulogin(@RequestParam("admin")String admin,
                          @RequestParam("adminpwd")String adminpwd,
                          Model model,
                          Map<String,Object> map,
                          HttpSession session,
                          HttpServletRequest request){
        Admin admin1 = adminDao.findByAdmin(admin);
        if(admin1 == null){
            map.put("msg","*请输入正确的用户名*");
            return "admin/login";
        }else {
            if(admin1.getAdminpwd().equals(adminpwd)){
                model.addAttribute("admin",admin1.getAdmin());
                session.setAttribute("loginAdmin",admin);
                return "redirect:/admin/index";
            } else {
                map.put("msg","*用户名密码错误*");
                return "admin/login";
            }
        }
    }
    @GetMapping("/logout")
    private String logout(HttpSession session) {
        session.removeAttribute(WebMvcConfig.SESSION_KEY);
        return "redirect:/music";
    }

    //用户管理
    @GetMapping("/Cuser")
    private String Cuser(Model model){
        Iterable<Users> listus = usersDao.findAll();
        model.addAttribute("users",listus);
        return "/admin/Cuser";
    }
    @GetMapping("/edit")
    private String edit(Model model, String username){
        Users byUsername = usersDao.findByUsername(username);
        model.addAttribute("users",byUsername);
        return "/admin/edit";
    }
    @PostMapping("/update")
    private String update(Model model,Users inputUs){
        Users user1 = usersDao.findByUsername(inputUs.getUsername());
        user1.setUsername(inputUs.getUsername());
        user1.setGender(inputUs.getGender());
        user1.setAge(inputUs.getAge());
        user1.setNumber(inputUs.getNumber());
        usersDao.save(user1);
        return "redirect:/admin/Cuser";
    }
    @GetMapping("/delete")
    private String delete(Users inputUs){
        Users byUsername = usersDao.findByUsername(inputUs.getUsername());
        usersDao.delete(byUsername);
        return "redirect:/admin/Cuser";
    }

    //歌曲管理
    @GetMapping("/Cmusic")
    private String Cmusic(Model model){
        Iterable<Music> listmu = musicDao.findAll();
        model.addAttribute("musics",listmu);
        Iterable<Leibie> listliebie = leibieDao.findAll();
        model.addAttribute("leibies",listliebie);
        return "/admin/Cmusic";
    }
    @PostMapping("/getLeibie")
    private String getLeibie(String leibie,Integer musicId){
        System.out.println("专辑"+leibie+"----添加"+musicId);
        musicDao.update(leibie,musicId);
        return "redirect:/admin/Cmusic";
    }
    @GetMapping("/Mdelete")
    private String Mdelete(Music inputMc){
        Music musicname = musicDao.findByMusicname(inputMc.getMusicname());
        musicDao.delete(musicname);
        return "redirect:/admin/Cmusic";

    }
    //歌单管理
    @GetMapping("/CmusicList")
    private String CmusicList(){
        return "/admin/CmusicList";
    }
    //歌曲上传
    @PostMapping(value = "/CmusicList")
    public String upload(HttpSession session,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam("lrc")MultipartFile lrc,
                         @RequestParam("songimg") MultipartFile songimg,
                         @RequestParam("targetUriMp3") String targetUriMp3,
                         @RequestParam("targetUriImage") String targetUriImage,
                         @RequestParam("targetUriText") String targetUriText,
                         String singer,
                         String musicname,
                         String leibiename,
                         String time) {
        Leibie byLeibiename = leibieDao.findByLeibiename(leibiename);
        Music ms = new Music();
        if (file.isEmpty()) {
            session.setAttribute("msg","上传文件失败");
        }

        String fileName = file.getOriginalFilename();
        String lrcName = lrc.getOriginalFilename();
        String songimgName = songimg.getOriginalFilename();
            if (fileName.endsWith(".mp3")) {
                File dest1 = new File( targetUriMp3,fileName);
                String dest =  dest1.getName();
                if (dest.endsWith(".mp3")) {
                    try {
                        file.transferTo(dest1);
                        session.setAttribute("msg","上传成功！");
                    } catch (IOException e) {

                    }
                }
            }

        if (lrcName.endsWith(".txt")) {
            log.error("111",targetUriText);
            File dest1 = new File( targetUriText,lrcName);
            String dest =  dest1.getName();
            if (dest.endsWith(".txt")) {
                try {
                    lrc.transferTo(dest1);
                    session.setAttribute("msg","上传成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        List<String> fileSuffix = new ArrayList<>();
        fileSuffix.add(".jpeg");
        fileSuffix.add(".png");
        fileSuffix.add(".jpg");
        fileSuffix.forEach(f -> {
        if (songimgName.endsWith(f)) {
            File dest1 = new File( targetUriImage,songimgName);
            try {
                songimg.transferTo(dest1);
                session.setAttribute("msg","上传成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        });
        ms.setMusicname(musicname);
        ms.setSinger(singer);
        ms.setTime(time);
        ms.setMusicimg("/music/imgbook/"+songimgName);
        ms.setLrc("/music/lrcbook/"+lrcName);
        ms.setUrl("/music/songbook/"+fileName);
//        ms.setLeibie(byLeibiename);
        musicDao.save(ms);
        return "redirect:/admin/CmusicList";
    }

    //专辑管理
    @GetMapping("/Clist")
    private String Clist(){
        return "/admin/Clist";
    }
    //评论管理
    @GetMapping("/Ccomment")
    private String Ccomment(Model model,Comments cominp){
        Iterable<Comments> all = commentsDao.findAll();
        model.addAttribute("comlist", all);
        return "/admin/Ccomment";
    }
    @GetMapping("/deleteBycom")
    private String deleteBycom(Integer id){
        commentsDao.deleteById(id);
        return "redirect:/admin/Ccomment";
    }
    //推送管理
    @GetMapping("/Clike")
    private String Clike(){
        return "/admin/Clike";
    }
}
