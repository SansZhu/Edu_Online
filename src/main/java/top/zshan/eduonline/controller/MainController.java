package top.zshan.eduonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author SansZhu
 * @create 2022/3/24 17:20
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String indexPage(){
        return "redirect:/index.html";
    }

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }

    @GetMapping("/main.html")
    public String main(){
        return "manager/admin";
    }




}
