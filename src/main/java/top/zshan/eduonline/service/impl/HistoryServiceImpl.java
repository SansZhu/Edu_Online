package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.History;
import top.zshan.eduonline.mapper.HistoryMapper;
import top.zshan.eduonline.service.HistoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SansZhu
 * @create 2022/3/31 20:55
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryMapper historyMapper;
    @Override
    public void updateHistory(Integer userId, Integer courseId, Integer videoOrder) {
        boolean isWatched = false;
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<History> histories = historyMapper.selectList(queryWrapper);
        for (History h :
                histories) {
           if (h.getCourseId() == courseId){
               isWatched = true;
           }
        }
        if (isWatched){
            QueryWrapper<History> queryWrapper1 = new QueryWrapper<>();
            Map<String,Integer> map = new HashMap<>();
            map.put("user_id",userId);
            map.put("course_id",courseId);
            queryWrapper1.allEq(map);
            historyMapper.update(new History(videoOrder),queryWrapper1);
        }else {
           historyMapper.insert(new History(userId, courseId, null, videoOrder));
        }
    }
}
