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
    private String nickname;

    public User() {
    }


    public User(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public User(String userEmail, String userPhone, Integer authority, String nickname) {
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.authority = authority;
        this.nickname = nickname;
    }

    public User(String userEmail, String userPhone, String nickname) {
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.nickname = nickname;
    }

    public User(Integer userId, String userName, String userEmail, String userPhone, String userPhoto, Integer authority, String password, String nickname) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPhoto = userPhoto;
        this.authority = authority;
        this.password = password;
        this.nickname = nickname;
    }

    public User(String userName, String userEmail, String userPhone, String userPhoto, Integer authority, String password, String nickname) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPhoto = userPhoto;
        this.authority = authority;
        this.password = password;
        this.nickname = nickname;
    }
}
