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
    private Integer teachId;
    private String courseType;
    private String detail;
    private String courseName;

    public Course(String coursePhoto) {

        this.coursePhoto = coursePhoto;
    }

    public Course(Integer teachId, String courseType, String detail, String courseName) {
        this.teachId = teachId;
        this.courseType = courseType;
        this.detail = detail;
        this.courseName = courseName;
    }

    public Course() {
    }

    public Course(Integer courseId, String coursePhoto, Integer teachId, String courseType, String detail, String courseName) {
        this.courseId = courseId;
        this.coursePhoto = coursePhoto;
        this.teachId = teachId;
        this.courseType = courseType;
        this.detail = detail;
        this.courseName = courseName;
    }
}
