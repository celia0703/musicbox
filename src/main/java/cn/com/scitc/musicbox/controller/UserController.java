package cn.com.scitc.musicbox.controller;

import cn.com.scitc.musicbox.dao.CollectionDao;
import cn.com.scitc.musicbox.dao.CommentsDao;
import cn.com.scitc.musicbox.dao.MusicDao;
import cn.com.scitc.musicbox.dao.UsersDao;
import cn.com.scitc.musicbox.model.Comments;
import cn.com.scitc.musicbox.model.Music;
import cn.com.scitc.musicbox.model.Users;
import cn.com.scitc.musicbox.model.Collection;
import cn.com.scitc.musicbox.myconfig.WebMvcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping({"/user","/"})
public class UserController {
    @Autowired
    UsersDao usersDao;
    @Autowired
    CollectionDao collectionDao;
    @Autowired
    MusicDao musicDao;
    @Autowired
    CommentsDao commentsDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/login")
    private String login(){
        return  "/user/login";
    }
    //登录
    @PostMapping("/ulogin")
    private String loginpage(@RequestParam("username")String username,
                             @RequestParam("userpwd")String userpwd,
                             Model model,
                             Map<String,Object> map,
                             HttpSession session,
                             HttpServletRequest request,
                             String yzm) {
        Users users = usersDao.findByUsername(username);
        // 获取验证码
        String code = (String) request.getSession().getAttribute("code");
//        如果数据库中未查到该账号
            if(users==null){
            map.put("msg","*请输入正确的用户名*");
            return "/user/login";
        }else {
            if (users.getUsername().equals(username)&users.getUserpwd().equals(userpwd)){
                //如果密码于用户匹配成功
                //登录成功，防止表单重复提  交
                //拦截器，只要登录就可以存在Session里面
                model.addAttribute("username",users.getUsername());
                session.setAttribute("loginUser",username);
                return "redirect:/music/music";
            }
//            if(!code.equalsIgnoreCase(yzm.toLowerCase())){
//                request.setAttribute("errorMessage", "验证码错误！");
//                return "/user/login";
//            }
            else  {
                //如果用户名密码不匹配
//              model.addAttribute("error","登录失败");
//                request.setAttribute("msg","验证码错误！");
                map.put("msg","*用户密码错误*");
                return "/user/login";
            }

        }
    }
            //登出
            @GetMapping("/logout")
            private String logout(HttpSession session){
                session.removeAttribute(WebMvcConfig.SESSION_KEY);
                return "redirect:/music";
            }
            //注册
            @GetMapping("/register")
            private String register(){
                return "user/register";
            }
            //
            @PostMapping("/uregister")
            private String uregister(Users inputUs,HttpSession session,Model model){
                Users us = new Users();
                us.setUsername(inputUs.getUsername());
                us.setUserpwd(inputUs.getUserpwd());
                us.setGender(inputUs.getGender());
                us.setAge(inputUs.getAge());
                us.setNumber(inputUs.getNumber());
                usersDao.save(us);
                model.addAttribute("username",us.getUsername());
                return "user/login";
            }
    //我喜欢
    @GetMapping("/myZoe")
    private String myfavorite(Model model, HttpSession session){
//        Collection collection=new Collection();
        String username =(String) session.getAttribute("loginUser");
//        List<Collection> byCollection = collectionDao.findByUsersUsername(username);
//        model.addAttribute("list",byCollection);
//        List<Music> musiclist=new ArrayList<>();
//        for(Collection collection1:byCollection){
//            List<Music> byMusicMusicname = musicDao.findByMusicMusicname(collection1.getMusicMusicname());
//            CollectionContainsMusic containsMusic = new CollectionContainsMusic();
//            containsMusic.setMusic(byMusicMusicname);
//            containsMusic.setCollection(byCollection);
////            List<CollectionContainsMusic> musicList = new ArrayList<>();
////            musicList.add(containsMusic);
//            for(Music music: containsMusic.getMusic()){
//                musiclist.add(music);
//            }

//        }
//        model.addAttribute("list1",musiclist);
        List<Music> musics = musicDao.findAllByUsername(username);
        model.addAttribute("list",musics);
        return "/user/myZoe";
    }
    @GetMapping("/collect")
    private String collect(@RequestParam("name") String name, HttpSession session){
        String username= (String) session.getAttribute("loginUser");
        logger.info("name:" + name);
        Collection collection=new Collection();
        List<Collection> collection1=collectionDao.findAllByMusicMusicnameAndUsersUsername(name,username);
        if(username == null) {
            return "redirect:/user/login";
        }
            if (collection1.size() == 0) {
                collection.setUsersUsername(username);
                collection.setMusicMusicname(name);
                collectionDao.save(collection);
                session.removeAttribute(WebMvcConfig.MSG);
                session.setAttribute("msg", "收藏成功!");
                logger.info("1", session.getAttribute("msg"));
            } else {
                session.removeAttribute(WebMvcConfig.MSG);
                session.setAttribute("msg", "已收藏!");
                logger.info("2", session.getAttribute("msg"));
            }



        return "redirect:/music/music";
}
    @GetMapping("/deleteLike")
    private String delete(Integer id){
        return "redirect:/user/myZoe";
    }



    //我的评论
    @GetMapping("/mycomment")
    private String mycomment(HttpSession session,Model model){
        String username=(String)session.getAttribute("loginUser");
        List<Comments> byUsersUsername = commentsDao.findByUsersUsername(username);
        model.addAttribute("mycom",byUsersUsername);
        return "/user/mycomment";
    }
    //个人信息
    @GetMapping("/mine")
    private String mine(){
        return "/user/mine";
    }
    //歌单管理
    @GetMapping("/mysonglist")
    private String Ulist(){
        return "/user/mysonglist";
    }

    @RequestMapping(value = "/getUsername")
    @ResponseBody
    public String userPage(HttpSession session) {
        String username = (String) session.getAttribute("loginUser");
        if (username!= null) {
            return username;
        }
        return  "error";
    }
}
