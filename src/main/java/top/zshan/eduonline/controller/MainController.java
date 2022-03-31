package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.CourseService;
import top.zshan.eduonline.service.impl.FileServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/24 17:20
 */
@Controller
public class MainController {

    @Autowired
    CourseService courseService;



    @GetMapping("/")
    public String indexPage(){
        return "redirect:/index.html";
    }

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }


    @GetMapping("/main.html")
    public String main(Model model){
        List<Course> mainCourse = courseService.getMainPageCourse();
        model.addAttribute("maincourse",mainCourse);
        System.out.println(mainCourse);
//        model.addAttribute("msg","111111111111111111");
        return "main/main";
    }
    @GetMapping("/admin")
    public String admin(HttpSession session,Model model){
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Integer authority = user.getAuthority();
            if (authority == 1) {
                return "manager/admin";
            }else {
                model.addAttribute("error","此页面不存在哦，请返回主页！");
                return "error/4xx";
            }

        }else {
            model.addAttribute("error","此页面不存在哦，请返回主页！");
            return "error/4xx";
        }
    }




}
