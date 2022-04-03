package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.impl.FileServiceImpl;
import top.zshan.eduonline.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/28 19:39
 */
@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    FileServiceImpl fileService;

//get查询
    @GetMapping(value = {"/user_manager","/user_manager/**"})
    public String userManager(HttpSession session,Model model){
        List<User> allUser = userService.getAllUser();
        model.addAttribute("alluser",allUser);
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Integer authority = user.getAuthority();
            if (authority == 1) {
                return "manager/user_manager";
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
    @PostMapping("/user_manager")
    public Msg saveUser(@RequestParam("userName") String username,
                        @RequestParam("password") String password,
                        @RequestParam("nickName") String nickname,
                        @RequestParam("userEmail") String email,
                        @RequestParam("userPhone") String phone,
                        @RequestParam("userPhoto") MultipartFile photo
                        ){
//        System.out.println("asdasd");
        String photoName = fileService.saveFileUser(photo);
        String photoUrl= "\\userimg\\"+photoName;
        boolean b = userService.insertUserForOne(new User(username, email, phone, photoUrl, 0, password, nickname));
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

    @ResponseBody
    @RequestMapping(value = "/user_manager/{userId}",method = RequestMethod.DELETE)
    public Msg deleteUser(@PathVariable("userId")Integer userId){
        boolean b = userService.deleteUser(userId);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
    @ResponseBody
    @RequestMapping(value = "/selectUser/{userId}",method = RequestMethod.GET)
    public User selectUserForOne(@PathVariable("userId")Integer userId){
        User userForOne = userService.getUserForOne(userId);
       return userForOne;
    }

    @ResponseBody
    @PostMapping(value = "/editUser/{userId}")
    public Msg updateUser(@PathVariable("userId") Integer userId,
                          @RequestParam("nickName") String nickname,
                          @RequestParam("userEmail") String email,
                          @RequestParam("userPhone") String phone,
                          @RequestParam("authority") Integer authority){
        boolean b = userService.updateUser(userId, nickname, email, phone,authority);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @PostMapping("/edit_photo/{userId}")
    public Msg saveUser(@RequestParam("userId") Integer userId,
                        @RequestParam("userPhoto") MultipartFile photo){
        String photoName = fileService.saveFileUser(photo);
        String photoUrl= "\\userimg\\"+photoName;
        boolean b = userService.updatePhoto(userId, photoUrl);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }
    @ResponseBody
    @GetMapping("/userIsHas")
    public Integer userIsHas(@RequestParam("userName")String userName){
//        true就是没有相同用户名，可用
        boolean userHas = userService.isUserHas(userName);
        if (userHas){
            return 1;
        }
        return 0;
    }

}
