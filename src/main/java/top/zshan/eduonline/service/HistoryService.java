package top.zshan.eduonline.service;

import top.zshan.eduonline.bean.History;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/31 20:54
 */
public interface HistoryService {
//    更新历史记录
    void updateHistory(Integer userId,Integer courseId,Integer videoOrder);
    List<History> getHistory(Integer userId);
}
