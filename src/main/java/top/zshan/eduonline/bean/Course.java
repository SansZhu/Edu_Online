package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:12
 */
@Data
public class Course {
    private Integer courseId;
    private String coursePhoto;
    private String teachId;
    private String courseType;
    private String detail;
}
