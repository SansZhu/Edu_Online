package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:09
 */
//lombok注解实现自动实现get，set方法
@Data
public class User {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPhoto;
    private Integer authority;
    private String password;

    public User() {
    }

    public User(Integer userId, String userName, String userEmail, String userPhone, String userPhoto, Integer authority, String password) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPhoto = userPhoto;
        this.authority = authority;
        this.password = password;
    }
}
