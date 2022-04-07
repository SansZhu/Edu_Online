package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.UserService;
import top.zshan.eduonline.service.impl.CountServiceImpl;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

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
    @Autowired
    CountServiceImpl countService;
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
        System.out.println(confirmUser);
        if (confirmUser != null){
            String password = confirmUser.getPassword();
            String loginPassword = user.getPassword();
            byte[] passwordBytes = loginPassword.getBytes(StandardCharsets.UTF_8);
            String passwordEncryption = DigestUtils.md5DigestAsHex(passwordBytes);
            if (password.equals(passwordEncryption)){
                session.setAttribute("user",confirmUser);
                countService.setCountDaily();
                return "redirect:/index.html";
            }else {
                model.addAttribute("msg","密码错误！");
                return "login/login";
            }
        }else {
            model.addAttribute("msg","账号不存在！");
            return "login/login";
        }


    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }



}
