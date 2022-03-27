package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @author SansZhu
 * @create 2022/3/27 21:38
 */
@Controller
public class LoginController {

//    自动注入jdbcTemplate
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

//    进入login页
    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }

//    点击登录进行处理，密码正确进入首页，错误返回提示账号或密码错误
    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
        String userName = user.getUserName();
        User confirmUser = userService.confirmUser(userName);
        String password = confirmUser.getPassword();
        if (password.equals(user.getPassword())){
            session.setAttribute("user",confirmUser);
            return "redirect:/index.html";
        }else {
            model.addAttribute("msg","账号或密码错误！");
            return "login/login";
        }



    }


}
