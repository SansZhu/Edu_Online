package top.zshan.eduonline.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zshan.eduonline.bean.*;
import top.zshan.eduonline.service.impl.CountServiceImpl;
import top.zshan.eduonline.service.impl.CourseServiceImpl;
import top.zshan.eduonline.service.impl.ViewsServiceImpl;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/4/1 22:39
 */
@Controller
public class CountController {
    @Autowired
    CountServiceImpl countService;
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    ViewsServiceImpl viewsService;
    @GetMapping("/count")
    public String count(HttpSession session, Model model,@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Integer authority = user.getAuthority();
            if (authority == 1) {
                Page<Course> coursesPage = new Page(pn, 6);
                Page<Course> page = courseService.page(coursesPage, null);
                Long allCountNum = countService.getAllCountNum();
                Long allViews = viewsService.getAllViews();
                model.addAttribute("allCountNum",allCountNum);
                model.addAttribute("allViews",allViews);
                model.addAttribute("page",page);
                return "manager/count";
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
    @GetMapping("/getLoginCount")
    public String getLoginCountByJson() throws JsonProcessingException {
//        jackson的对象映射器，就是一个类，使用它可以直接将对象转换为字符串
        ObjectMapper objectMapper = new ObjectMapper();
        List<LoginCountChart> loginCountCharts = new LinkedList<>();
        List<Count> chartCount = countService.getAllCountToChart();
        for (Count c :
                chartCount) {
            DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
            Date date = c.getDate();
            String format = dateFormat.format(date);
            loginCountCharts.add(new LoginCountChart(format,c.getLoginCount()));
        }
        String loginCountChartsJson = objectMapper.writeValueAsString(loginCountCharts);
        return loginCountChartsJson;
    }

    @ResponseBody
    @GetMapping("/getCoursePlayCount")
    public String getCoursePlayCountJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn) throws JsonProcessingException {
//        jackson的对象映射器，就是一个类，使用它可以直接将对象转换为字符串
        ObjectMapper objectMapper = new ObjectMapper();
        List<CoursePlayCount> courseCountCharts = new LinkedList<>();
        List<Course> allCourse = courseService.getAllCourse();
        Page<Course> coursesPage = new Page(pn, 6);
        Page<Course> page = courseService.page(coursesPage, null);
        for (Course c :
                page.getRecords()) {
            Integer courseId = c.getCourseId();
            Integer views = viewsService.getViews(courseId);
            courseCountCharts.add(new CoursePlayCount(c.getCourseName(),views));
        }

        String loginCountChartsJson = objectMapper.writeValueAsString(courseCountCharts);
        return loginCountChartsJson;
    }
}
