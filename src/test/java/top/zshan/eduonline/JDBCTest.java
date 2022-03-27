package top.zshan.eduonline;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.mapper.UserMapper;

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
}
