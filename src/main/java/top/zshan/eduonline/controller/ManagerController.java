package top.zshan.eduonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author SansZhu
 * @create 2022/3/26 20:51
 */
@Controller
public class ManagerController {

    @GetMapping("/fontawesome")
    public String fontawesome(){
        return "manager/fontawesome";
    }


    @GetMapping("/count")
    public String count(){
        return "manager/count";
    }
}
