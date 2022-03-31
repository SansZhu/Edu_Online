package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.bean.Video;
import top.zshan.eduonline.service.impl.FileServiceImpl;
import top.zshan.eduonline.service.impl.VideoServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/30 21:09
 */
@Controller
public class VideoController {
    @Autowired
    VideoServiceImpl videoService;
    @Autowired
    FileServiceImpl fileService;

    @GetMapping(value = {"/course_video_manager","/course_video_manager/**"})
    public String courseAddManager(HttpSession session, Model model, @RequestParam("courseId") Integer courseId){
        List<Video> videoByCourseId = videoService.getVideoByCourseId(courseId);
        model.addAttribute("videoByCourseId",videoByCourseId);
        model.addAttribute("courseId",courseId);
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Integer authority = user.getAuthority();
            if (authority == 1) {
                return "manager/course_video_manager";
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
    @PostMapping("/course_video_manager")
    public Msg addVideo(@RequestParam("videoTitle")String videoTitle,
                        @RequestParam("videoOrder") Integer videoOrder,
                        @RequestParam("inputVideo")MultipartFile video,
                        @RequestParam("courseId") Integer courseId){
        String videoName = fileService.saveFileVideo(video);
        String videoUrl= "/coursevideo/"+videoName;
        boolean b = videoService.insertCourseForOne(new Video(null,videoOrder,courseId,videoUrl,videoTitle));
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
    @ResponseBody
    @RequestMapping(value = "/course_video_manager/{videoId}",method = RequestMethod.DELETE)
    public Msg deleteVideo(@PathVariable("videoId")Integer videoId){
        boolean b = videoService.deleteVideo(videoId);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }




}
