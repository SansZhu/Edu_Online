package top.zshan.eduonline.service;

import top.zshan.eduonline.bean.Video;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/30 21:12
 */
public interface VideoService {
    List<Video> getVideoByCourseId(Integer courseId);
    boolean insertCourseForOne(Video video);
    boolean deleteVideo(Integer videoId);
    Video getVideoForOne(Integer courseId,Integer videoOrder);
}
