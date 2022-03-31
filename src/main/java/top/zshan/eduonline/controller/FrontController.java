package top.zshan.eduonline.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.bean.Video;
import top.zshan.eduonline.mapper.CourseMapper;
import top.zshan.eduonline.service.impl.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/30 23:31
 */
@Controller
public class FrontController {
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    VideoServiceImpl videoService;
    @Autowired
    CollectionServiceImpl collectionService;
    @Autowired
    ViewsServiceImpl viewsService;
    @Autowired
    HistoryServiceImpl historyService;
    @GetMapping("/course_list")
    public String courseList(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        List<Course> allCourse = courseService.getAllCourse();
//        分页查询

        Page<Course> coursesPage = new Page(pn, 6);

//        分页查询的结果
        Page<Course> page = courseService.page(coursesPage, null);
        model.addAttribute("allCourse",allCourse);
        model.addAttribute("page",page);
        return "front/course_list";

    }

    @GetMapping("/video")
    public String videoList(@RequestParam("courseId")Integer course,Model model,HttpSession session){
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Integer userId = user.getUserId();
            boolean collectionForCourse = collectionService.isCollectionForCourse(course, userId);
            model.addAttribute("isCollection",collectionForCourse);
        }

        Course courseForOne = courseService.getCourseForOne(course);
        List<Video> videoByCourseId = videoService.getVideoByCourseId(course);
        Integer views = viewsService.getViews(course);
        System.out.println(videoByCourseId);
        model.addAttribute("course",courseForOne);
        model.addAttribute("views",views);
        model.addAttribute("videoList",videoByCourseId);
        return "front/video";
    }

    @GetMapping("/videoPlayer")
    public String videPlayer(@RequestParam("courseId")Integer courseId,
                             @RequestParam("videoOrder")Integer videoOrder,
                             HttpSession session,Model model){
        if (session.getAttribute("user") != null) {
            viewsService.updateViews(courseId);
//            更新用户历史记录
            User user = (User) session.getAttribute("user");
            historyService.updateHistory(user.getUserId(),courseId,videoOrder);
//            查询视频信息并添加
            Course courseForOne = courseService.getCourseForOne(courseId);
            List<Video> videoByCourseId = videoService.getVideoByCourseId(courseId);
            Video videoForOne = videoService.getVideoForOne(courseId, videoOrder);
            model.addAttribute("video",videoForOne);
            model.addAttribute("course",courseForOne);
            model.addAttribute("videoList",videoByCourseId);
            return "front/videoPlayer";
        }else {
            model.addAttribute("error","请先登录再观看视频哦！");
            return "error/5xx";
        }

    }
}
