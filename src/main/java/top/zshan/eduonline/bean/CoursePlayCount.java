package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/4/2 0:32
 */
@Data
public class CoursePlayCount {
    private String x;
    private Integer y;

    public CoursePlayCount(String courseName, Integer view) {
        this.x = courseName;
        this.y = view;
    }
}
