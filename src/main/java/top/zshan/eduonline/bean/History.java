package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:15
 */
@Data
public class History {
    private Integer userId;
    private Integer courseId;
    private Integer historyOrder;
    private Integer videoOrder;

    public History(Integer videoOrder) {
        this.videoOrder = videoOrder;
    }

    public History(Integer userId, Integer courseId, Integer historyOrder, Integer videoOrder) {
        this.userId = userId;
        this.courseId = courseId;
        this.historyOrder = historyOrder;
        this.videoOrder = videoOrder;
    }
}
