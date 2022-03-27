package top.zshan.eduonline.service;

import top.zshan.eduonline.bean.User;

/**
 * @author SansZhu
 * @create 2022/3/27 22:43
 */
public interface UserService {
//    根据用户名得到User
     User confirmUser(String userName);
     boolean confirmUserAndPassword(String userName,String password);
}
