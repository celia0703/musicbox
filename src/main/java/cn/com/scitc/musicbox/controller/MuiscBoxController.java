package cn.com.scitc.musicbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//主网页
@Controller
@RequestMapping("/index")
public class MuiscBoxController {
    @GetMapping("/musicbox")
    private String musicbox(){
        return "/index/musicbox";
    }
}
