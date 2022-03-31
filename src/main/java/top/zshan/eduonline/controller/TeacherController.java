package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.bean.Teacher;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.impl.FileServiceImpl;
import top.zshan.eduonline.service.impl.TeacherServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/29 22:43
 */
@Controller
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    FileServiceImpl fileService;

    @GetMapping(value = {"/teach_manager","/teacher_manager/**"})
    public String userManager(HttpSession session,Model model){
        List<Teacher> allTeacher = teacherService.getAllTeacher();
        model.addAttribute("allteacher",allTeacher);
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Integer authority = user.getAuthority();
            if (authority == 1) {
                return "manager/teach_manager";
            }else {
                model.addAttribute("error","此页面不存在哦，请返回主页！");
                return "error/4xx";
            }

        }else {
            model.addAttribute("error","此页面不存在哦，请返回主页！");
            return "error/4xx";
        }
    }

    @PostMapping("/teach_manager")
    @ResponseBody
    public Msg saveUser(@RequestParam("teachName") String teachName,
                        @RequestParam("teachEmail") String teachEmail,
                        @RequestParam("teachPhone") String teachPhone,
                        @RequestParam("teachPhoto") MultipartFile teachPhoto
    ){
//        System.out.println("asdasd");
        String photoName = fileService.saveFileTeacher(teachPhoto);
        String photoUrl= "\\teachimg\\"+photoName;
        boolean b = teacherService.insertTeacherForOne(new Teacher(null,teachName, teachEmail, teachPhone, photoUrl));
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/teach_manager/{teachId}",method = RequestMethod.DELETE)
    public Msg deleteUser(@PathVariable("teachId")Integer teachId){
        boolean b = teacherService.deleteTeacher(teachId);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

    @ResponseBody
    @RequestMapping(value = "/selectTeach/{teachId}",method = RequestMethod.GET)
    public Teacher selectUserForOne(@PathVariable("teachId")Integer teachId){
        Teacher teacherForOne = teacherService.getTeacherForOne(teachId);
        return teacherForOne;
    }

    @ResponseBody
    @PostMapping(value = "/editTeach/{teachId}")
    public Msg updateUser(@PathVariable("teachId") Integer teachId,
                          @RequestParam("teachName") String teachName,
                          @RequestParam("teachPhone") String teachPhone,
                          @RequestParam("teachEmail") String teachEmail){
        boolean b = teacherService.updateTeacher(teachId, teachName, teachPhone, teachEmail);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @ResponseBody
    @PostMapping("/teach_edit_photo/{teachId}")
    public Msg saveUser(@PathVariable("teachId") Integer teachId,
                        @RequestParam("teachPhoto") MultipartFile teachPhoto){
        String photoName = fileService.saveFileTeacher(teachPhoto);
        String photoUrl= "\\teachimg\\"+photoName;
        boolean b = teacherService.updatePhoto(teachId, photoUrl);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

}
