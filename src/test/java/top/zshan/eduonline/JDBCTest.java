package top.zshan.eduonline;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.mapper.CourseMapper;
import top.zshan.eduonline.mapper.UserMapper;
import top.zshan.eduonline.service.CourseService;
import top.zshan.eduonline.service.impl.CollectionServiceImpl;
import top.zshan.eduonline.service.impl.CourseServiceImpl;
import top.zshan.eduonline.service.impl.UserServiceImpl;
import top.zshan.eduonline.service.impl.ViewsServiceImpl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SansZhu
 * @create 2022/3/27 21:49
 */
@SpringBootTest
public class JDBCTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserMapper userMapper;


    @Test
    public void testJDBC(){
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",3);
        User user = userMapper.selectOne(queryWrapper);
//        int i = userMapper.insert(new User(null, "小明", "xiaoming@qq.com", "12211221122", "C:\\Users\\diemo\\Desktop\\Flash.jpg", 0, "123123"));
//        System.out.println(i);
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",3);
        List<User> users = userMapper.selectByMap(map);
        if (users.isEmpty()){
            System.out.println("asdasd");
        }
        if (user == null){
            System.out.println("zxcvzxcvzxcv");
        }
        System.out.println(users);
    }

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseServiceImpl courseServiceimpl;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    CollectionServiceImpl collectionService;
@Autowired
    ViewsServiceImpl viewsService;

@Test
    public void getAllCourse() {
        List<Course> courses = courseMapper.selectList(null);
    List<Course> allCourse = courseServiceimpl.getAllCourse();
//        System.out.println(allCourse);
    QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("course_id");
    queryWrapper.last("limit 0,6");
    List<Course> maincourses = courseMapper.selectList(queryWrapper);
    System.out.println(maincourses);
    }

    @Test
    public void insertUserForOneTest(){
//        boolean b = userService.insertUserForOne(new User("xiaohong", "xiaohong@q.com", "11122221111", "\\userimg\\Flash.jpg", 0, "123123", "小红"));
        for (int i = 0; i < 100; i++) {

            userService.insertUserForOne(new User("ceshi"+i, "xiaohong@q.com", "11122221111", "\\userimg\\Flash.jpg", 0, "123123", "测试"+i));
        }

    }

    @Test
    public void testDelteUser(){
        boolean b = userService.deleteUser(103);
        System.out.println(b);
    }

    @Test
    public void testCollection(){
//        boolean collectionForCourse = collectionService.isCollectionForCourse(3, 1);
        boolean b = collectionService.addCollection(1, 1);
        System.out.println(b);
    }
    @Test
    public void testViews(){
        viewsService.updateViews(3);
    }

    @Test
    public void testDate(){
        long l = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(l);
//        date.setTime(date.getTime());
        System.out.println(dateFormat.format(date));
    }
}
