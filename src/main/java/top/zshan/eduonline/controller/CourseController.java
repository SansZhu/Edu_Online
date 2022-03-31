package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.bean.Teacher;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.impl.CourseServiceImpl;
import top.zshan.eduonline.service.impl.FileServiceImpl;
import top.zshan.eduonline.service.impl.TeacherServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/30 16:32
 */
@Controller
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    FileServiceImpl fileService;
    @GetMapping(value = {"/course_manager","/course_manager/**"})
    public String courseManager(HttpSession session,Model model){
        List<Course> allCourse = courseService.getAllCourse();
        List<String> allTeacherName = new ArrayList<>();
        for (Course course :
                allCourse) {
            Integer teachId = course.getTeachId();
            String s = teacherService.selectTeacherName(teachId);
            allTeacherName.add(s);
        }
        model.addAttribute("allCourse",allCourse);
        model.addAttribute("allTeacherName",allTeacherName);
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Integer authority = user.getAuthority();
            if (authority == 1) {
                return "manager/course_manager";
            }else {
                model.addAttribute("error","此页面不存在哦，请返回主页！");
                return "error/4xx";
            }

        }else {
            model.addAttribute("error","此页面不存在哦，请返回主页！");
            return "error/4xx";
        }

    }
    @ResponseBody
    @GetMapping("/tname")
    public Msg getTeacherName(){
        List<Teacher> allTeacher = teacherService.getAllTeacher();
        return Msg.success().add("allTeacher",allTeacher);
    }
    @ResponseBody
    @GetMapping("/courseType")
    public Msg getCourseType(){
        List<Course> allCourse = courseService.getAllCourseDistinct();
        return Msg.success().add("coursess",allCourse);
    }

    @PostMapping("/course_manager")
    @ResponseBody
    public Msg saveCourse(@RequestParam("courseName") String courseName,
                        @RequestParam("teachId") Integer teachId,
                        @RequestParam("courseType") String courseType,
                        @RequestParam("courseDetail") String courseDetail,
                        @RequestParam("coursePhoto") MultipartFile coursePhoto
    ){
//        System.out.println("asdasd");
        String photoName = fileService.saveFileCourse(coursePhoto);
        String photoUrl= "/courseimg/"+photoName;
        boolean b = courseService.insertCourseForOne(new Course(null,photoUrl, teachId, courseType,courseDetail,courseName));
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectCourse/{courseId}",method = RequestMethod.GET)
    public Course selectUserForOne(@PathVariable("courseId")Integer courseId){
        Course courseForOne = courseService.getCourseForOne(courseId);
        return courseForOne;
    }
    @ResponseBody
    @PostMapping(value = "/editCourse/{courseId}")
    public Msg updateCourse(@PathVariable("courseId") Integer courseId,
                          @RequestParam("editCourseType") String courseType,
                          @RequestParam("editTeachId") Integer teachId,
                          @RequestParam("editCourseName") String courseName,
                          @RequestParam("editCourseDetail") String detail){
        boolean b = courseService.updateCourse(courseId,teachId,courseType,detail,courseName);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    @ResponseBody
    @RequestMapping(value = "/course_manager/{courseId}",method = RequestMethod.DELETE)
    public Msg deleteUser(@PathVariable("courseId")Integer courseId){
        boolean b = courseService.deleteCourse(courseId);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
    @ResponseBody
    @PostMapping("/course_edit_photo/{courseId}")
    public Msg editCoursePhoto(@PathVariable("courseId") Integer courseId,
                        @RequestParam("coursePhoto") MultipartFile coursePhoto){
        String photoName = fileService.saveFileCourse(coursePhoto);
        String photoUrl= "/courseimg/"+photoName;
        boolean b = courseService.updatePhoto(courseId, photoUrl);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
}
