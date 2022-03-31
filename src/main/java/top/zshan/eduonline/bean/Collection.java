package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:16
 */
@Data
public class Collection {
    private Integer userId;
    private Integer courseId;
    private Integer collectionId;

    public Collection(Integer userId, Integer courseId, Integer collectionId) {
        this.userId = userId;
        this.courseId = courseId;
        this.collectionId = collectionId;
    }
}
