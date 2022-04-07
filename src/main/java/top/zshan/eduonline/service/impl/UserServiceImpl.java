package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.mapper.UserMapper;
import top.zshan.eduonline.service.UserService;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @Override
    public boolean insertUserForOne(User user) {
        int insert = userMapper.insert(user);
        if (insert == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        int delete = userMapper.delete(queryWrapper);
        if (delete == 1){
            return true;
        }else
        return false;
    }

    @Override
    public User getUserForOne(Integer userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public boolean updateUser(Integer userId, String nickname, String email, String phone,Integer authority) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        int update = userMapper.update(new User(email, phone, authority,nickname), queryWrapper);
        if (update == 1){
            return true;
        }else
            return false;

    }

    @Override
    public boolean updatePhoto(Integer userId, String photo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        int update = userMapper.update(new User(photo), queryWrapper);
        if (update == 1){
            return true;
        }else
            return false;
    }

    @Override
    public boolean isUserHas(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(Integer userId, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        User user = new User();
        user.setPassword(password);
        int update = userMapper.update(user, queryWrapper);
        if (update == 1){
            return true;
        }else
            return false;
    }
}
