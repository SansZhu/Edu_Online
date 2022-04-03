package top.zshan.eduonline.service;

import top.zshan.eduonline.bean.Count;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/4/1 22:20
 */
public interface CountService {
    void setCountDaily();
    List<Count> getAllCount();
    List<Count> getAllCountToChart();
    Long getAllCountNum();

}
