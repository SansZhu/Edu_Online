package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.Video;
import top.zshan.eduonline.mapper.VideoMapper;
import top.zshan.eduonline.service.VideoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SansZhu
 * @create 2022/3/30 21:12
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Override
    public List<Video> getVideoByCourseId(Integer courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<Video> videos = videoMapper.selectList(queryWrapper);
        return videos;
    }

    @Override
    public boolean insertCourseForOne(Video video) {
        int insert = videoMapper.insert(video);
        if (insert == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteVideo(Integer videoId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id",videoId);
        int delete = videoMapper.delete(queryWrapper);
        if (delete == 1){
            return true;
        }else
            return false;
    }

    @Override
    public Video getVideoForOne(Integer courseId, Integer videoOrder) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("course_id",courseId);
        map.put("video_order",videoOrder);
        queryWrapper.allEq(map);
        Video video = videoMapper.selectOne(queryWrapper);
        return video;
    }

    @Override
    public boolean videoTitleIsHas(String videoTitle,Integer courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<Video> videos = videoMapper.selectList(queryWrapper);
        boolean flag = true;
        for (Video v :
                videos) {
             if(videoTitle.equals(v.getVideoTitle())){
                 flag = false;
             }
        }
        return flag;
    }

    @Override
    public boolean videoOrderIsHas(Integer videoOrder,Integer courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("course_id",courseId);
        map.put("video_order",videoOrder);
        queryWrapper.allEq(map);
        List<Video> videos = videoMapper.selectList(queryWrapper);
        if (videos.isEmpty()){
            return true;
        }
        return false;
    }
}
