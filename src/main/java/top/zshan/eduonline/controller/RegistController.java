package top.zshan.eduonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.bean.Msg;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.service.impl.FileServiceImpl;
import top.zshan.eduonline.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

/**
 * @author SansZhu
 * @create 2022/4/2 18:13
 */
@Controller
public class RegistController {
    @Autowired
    FileServiceImpl fileService;
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/regist")
    public String registPage(){

        return "login/regist";
    }

    @PostMapping("/regist")
    public String regist(@RequestParam("userName") String username,
                         @RequestParam("password") String password,
                         @RequestParam("nickName") String nickname,
                         @RequestParam("userEmail") String email,
                         @RequestParam("userPhone") String phone,
                         @RequestParam("userPhoto") MultipartFile photo,
                         Model model,
                         HttpSession session){
        String photoName = fileService.saveFileUser(photo);
        String photoUrl= "\\userimg\\"+photoName;
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        String passwordEncryption = DigestUtils.md5DigestAsHex(passwordBytes);
        boolean b = userService.insertUserForOne(new User(username, email, phone, photoUrl, 0, passwordEncryption, nickname));
        if (b){
            session.setAttribute("regist","regist");
            return "redirect:/regist_success";
        }else {
            model.addAttribute("msg","注册失败");
            return "login/regist";
        }
    }

    @GetMapping("regist_success")
    public String registSuccess(HttpSession session){
        Object regist = session.getAttribute("regist");
        if (regist == null){
            return "login/login";
        }
        return "login/regist_success";
    }
}
