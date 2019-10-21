package cn.com.scitc.musicbox.controller;

import cn.com.scitc.musicbox.dao.LeibieDao;
import cn.com.scitc.musicbox.dao.MusicDao;

import cn.com.scitc.musicbox.model.Leibie;
import cn.com.scitc.musicbox.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/music")
public class LeibieController {
    @Autowired
    private MusicDao musicDao;
    @Autowired
    private LeibieDao leibieDao;
    @GetMapping("/songlist")
        private String songlist(Model model, Integer id, HttpSession session){
        List<Music> musicList = musicDao.findAllByLeibieId(id);
        Optional<Leibie> leibie = leibieDao.findById(id);
        Leibie leibie1=leibie.get();
        model.addAttribute("music",musicList);
        model.addAttribute("leibie",leibie1);

//        List<Music> list1 = leibieDao.findById(id).get().getMusicList();
//        model.addAttribute("list",list1);
        return "music/songlist";
   }
}
