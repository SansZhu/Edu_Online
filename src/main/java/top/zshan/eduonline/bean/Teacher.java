package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:14
 */
@Data
public class Teacher {
    private Integer teachId;
    private String teachName;
    private String teachEmail;
    private String teachPhone;
    private String teachPhoto;

    public Teacher(Integer teachId, String teachName, String teachEmail, String teachPhone, String teachPhoto) {
        this.teachId = teachId;
        this.teachName = teachName;
        this.teachEmail = teachEmail;
        this.teachPhone = teachPhone;
        this.teachPhoto = teachPhoto;
    }

    public Teacher(String teachPhoto) {
        this.teachPhoto = teachPhoto;
    }

    public Teacher(String teachName, String teachEmail, String teachPhone) {
        this.teachName = teachName;
        this.teachEmail = teachEmail;
        this.teachPhone = teachPhone;
    }
}
