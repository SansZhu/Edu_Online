package top.zshan.eduonline.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.zshan.eduonline.bean.Collection;

/**
 * @author SansZhu
 * @create 2022/3/31 18:50
 */
public interface CollectionService{
    boolean isCollectionForCourse(Integer courseId,Integer userId);
    boolean cancelCollection(Integer courseId,Integer userId);
    boolean addCollection(Integer courseId,Integer userId);
}
