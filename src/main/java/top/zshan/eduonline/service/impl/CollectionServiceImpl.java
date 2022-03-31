package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.Collection;
import top.zshan.eduonline.mapper.CollectionMapper;
import top.zshan.eduonline.service.CollectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SansZhu
 * @create 2022/3/31 18:51
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public boolean isCollectionForCourse(Integer courseId, Integer userId) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("course_id",courseId);
        map.put("user_id",userId);
        queryWrapper.allEq(map);
        Collection collection = collectionMapper.selectOne(queryWrapper);
        System.out.println(collection);
        if (collection != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelCollection(Integer courseId, Integer userId) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("course_id",courseId);
        map.put("user_id",userId);
        queryWrapper.allEq(map);
        int delete = collectionMapper.delete(queryWrapper);
        if (delete == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean addCollection(Integer courseId, Integer userId) {
        int insert = collectionMapper.insert(new Collection(userId, courseId, null));
        if (insert ==1){
            return true;
        }
        return false;
    }
}
