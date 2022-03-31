package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.service.impl.CollectionServiceImpl;

/**
 * @author SansZhu
 * @create 2022/3/31 19:31
 */
@Controller
public class CollectionController {
    @Autowired
    CollectionServiceImpl collectionService;
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

}
