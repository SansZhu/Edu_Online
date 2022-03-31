package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:16
 */
@Data
public class Views {
    private Integer courseId;
    private Integer view;

    public Views(Integer courseId, Integer view) {
        this.courseId = courseId;
        this.view = view;
    }
}
