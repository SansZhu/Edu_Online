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

    @GetMapping("/user_manager")
    public String userManager(){
        return "manager/user_manager";
    }
    @GetMapping("/teach_manager")
    public String teachManager(){
        return "manager/teach_manager";
    }
    @GetMapping("/course_manager")
    public String courseManager(){
        return "manager/course_manager";
    }
    @GetMapping("/course_add_manager")
    public String courseAddManager(){
        return "manager/course_add_manager";
    }
    @GetMapping("/count")
    public String count(){
        return "manager/count";
    }
}
