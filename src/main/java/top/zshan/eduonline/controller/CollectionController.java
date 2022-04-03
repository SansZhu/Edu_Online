package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zshan.eduonline.bean.*;
import top.zshan.eduonline.service.impl.CollectionServiceImpl;
import top.zshan.eduonline.service.impl.CourseServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/31 19:31
 */
@Controller
public class CollectionController {
    @Autowired
    CollectionServiceImpl collectionService;
    @Autowired
    CourseServiceImpl courseService;
    @ResponseBody
    @GetMapping("/cancelCollection")
    public Msg cancelCollection(@RequestParam("userId")Integer userId,
                                @RequestParam("courseId")Integer courseId){
        boolean b = collectionService.cancelCollection(courseId, userId);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }
    @ResponseBody
    @GetMapping("/addCollection")
    public Msg accCollection(@RequestParam("userId")Integer userId,
                                @RequestParam("courseId")Integer courseId){
        boolean b = collectionService.addCollection(courseId, userId);
        if (b){
            return Msg.success();
        }
        return Msg.fail();
    }

    @GetMapping("/collection")
    public String collection(HttpSession session, Model model){
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            List<Collection> collection= collectionService.getAllCollectionCourseByUserId(user.getUserId());
            List<Course> courses = new LinkedList<>();
            if (!collection.isEmpty()) {
                for (Collection c:
                        collection ){
                    Course courseForOne = courseService.getCourseForOne(c.getCourseId());
//                    System.out.println(courseForOne);
                    courses.add(courseForOne);
                }
            }

            model.addAttribute("collection",collection);
            model.addAttribute("courses",courses);
            return  "front/collection";
        }else {
            model.addAttribute("error","请先登录再尝试进入哦！");
            return "error/4xx";
        }

    }

}
