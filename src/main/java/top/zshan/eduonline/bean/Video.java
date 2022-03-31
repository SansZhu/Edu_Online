package top.zshan.eduonline.bean;

import lombok.Data;

/**
 * @author SansZhu
 * @create 2022/3/27 22:13
 */
@Data
public class Video {
    private Integer videoId;
    private Integer videoOrder;
    private Integer courseId;
    private String videoUrl;
    private String videoTitle;

    public Video(Integer videoId, Integer videoOrder, Integer courseId, String videoUrl, String videoTitle) {
        this.videoId = videoId;
        this.videoOrder = videoOrder;
        this.courseId = courseId;
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
    }
}
