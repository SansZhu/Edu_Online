package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.History;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.bean.Video;
import top.zshan.eduonline.service.impl.CourseServiceImpl;
import top.zshan.eduonline.service.impl.HistoryServiceImpl;
import top.zshan.eduonline.service.impl.VideoServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/4/1 20:05
 */
@Controller
public class HistoryController {
    @Autowired
    HistoryServiceImpl historyService;
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    VideoServiceImpl videoService;
    @GetMapping("/history")
    public String history(HttpSession session, Model model){
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            List<History> history = historyService.getHistory(user.getUserId());
            List<Course> courses = new LinkedList<>();
            List<Video> videos = new LinkedList<>();
            if (!history.isEmpty()) {
                for (History h:
                         history ){
                    Video videoForOne = videoService.getVideoForOne(h.getCourseId(), h.getVideoOrder());
                    Course courseForOne = courseService.getCourseForOne(h.getCourseId());
//                    System.out.println(courseForOne);
                    courses.add(courseForOne);
                    videos.add(videoForOne);
                }
            }

            model.addAttribute("history",history);
            model.addAttribute("courses",courses);
            model.addAttribute("videos",videos);

            return  "front/history";
        }else {
            model.addAttribute("error","请先登录再尝试进入哦！");
            return "error/4xx";
        }

    }
}
