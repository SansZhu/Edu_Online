package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.mapper.UserMapper;
import top.zshan.eduonline.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SansZhu
 * @create 2022/3/27 22:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User confirmUser(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        User loginUser = userMapper.selectOne(queryWrapper);
        if (loginUser != null){
            return loginUser;
        }
        return null;
    }

    @Override
    public boolean confirmUserAndPassword(String userName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        User loginUser = userMapper.selectOne(queryWrapper);
        return false;
    }
}
