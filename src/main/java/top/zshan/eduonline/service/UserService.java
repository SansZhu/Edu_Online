package top.zshan.eduonline.service;

import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.User;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/27 22:43
 */
public interface UserService {
//    根据用户名得到User
     User confirmUser(String userName);
     boolean confirmUserAndPassword(String userName,String password);
     List<User> getAllUser();
     boolean insertUserForOne(User user);
     boolean deleteUser(Integer userId);
     User getUserForOne(Integer userId);
     boolean updateUser(Integer userId,String nickname,String email,String phone,Integer authority);
     boolean updatePhoto(Integer userId,String photo);
     boolean isUserHas(String userName);
}
