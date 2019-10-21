package cn.com.scitc.musicbox.controller;

import cn.com.scitc.musicbox.dao.CommentsDao;
import cn.com.scitc.musicbox.dao.LeibieDao;
import cn.com.scitc.musicbox.dao.MusicDao;
import cn.com.scitc.musicbox.model.Comments;
import cn.com.scitc.musicbox.model.Music;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping({"/music","/"})
public class MusicController {
    private @Autowired
    MusicDao musicDao;
    private @Autowired
    LeibieDao leibieDao;
    private @Autowired
    CommentsDao commentsDao;



    @GetMapping("/music")

    private String musicData(Model model) {
        List<Music> lover = musicDao.findByLeibiename("lover");
        List<Music> eng = musicDao.findByLeibiename("1989");
        List<Music> rep = musicDao.findByLeibiename("rep");
//        Object lover1 = JSON.toJSON(lover);
//        Object eng1 = JSON.toJSON(eng);
//        Object rep1 = JSON.toJSON(rep);
//        JSON j = new JSONObject();
        Object lover1 =  JSON.toJSON(lover);
        Object eng1 =  JSON.toJSON(eng);
        Object rep1 =  JSON.toJSON(rep);
        model.addAttribute("musiclist1",lover1);
        model.addAttribute("musiclist2",eng1);
        model.addAttribute("musiclist3",rep1);

        return "/music/music";
    }
    @GetMapping("/listen")
    private String listen(){
        return "/music/listen";
    }
    @GetMapping("/comment")
    private String comment(Model model, Comments comInp, @RequestParam(defaultValue = "0") Integer pageNum){
        Pageable pageable=PageRequest.of(pageNum,3);
        Page<Comments> com = commentsDao.findPage(pageable);
        model.addAttribute("totalPage",com.getTotalPages());
        model.addAttribute("com",com);
        return "/music/comment";
    }
    @PostMapping("/userComment")
    private String userComment(Comments comInp, Model model,HttpSession session,String date,String musicname){
        String username=(String)session.getAttribute("loginUser");
        Comments com=new Comments();
        com.setUsersUsername(username);
        com.setText(comInp.getText());
        com.setMusicMusicname(musicname);
        com.setTime(date);
        commentsDao.save(com);
        return "redirect:/music/comment";
    }

    //搜索
    @PostMapping("/searching")
    private String searching(Model model,
                             String musicname){
        List<Music> search = musicDao.findByMusicnameLike('%'+musicname+'%');
//        List<Music> search1 = musicDao.findBySingerLike('%'+musicname+'%');
        model.addAttribute("searchlist",search);
//        model.addAttribute("searchlist",search1);
        return "/music/search";
    }
    @GetMapping("/index")
    private String index(){
        return "/music/index";
    }
}
