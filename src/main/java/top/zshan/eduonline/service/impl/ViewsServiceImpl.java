package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.Views;
import top.zshan.eduonline.mapper.ViewsMapper;
import top.zshan.eduonline.service.VideoService;
import top.zshan.eduonline.service.ViewsService;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/31 20:56
 */
@Service
public class ViewsServiceImpl implements ViewsService {
    @Autowired
    ViewsMapper viewsMapper;
    @Override
    public void updateViews(Integer courseId) {
        QueryWrapper<Views> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Views views = viewsMapper.selectOne(queryWrapper);
        if (views == null){
            viewsMapper.insert(new Views(courseId, 1));
        }
        if (views != null){
            Integer view = views.getView();
            int newView = ++view;
            viewsMapper.update(new Views(courseId,newView),queryWrapper);
        }
    }

    @Override
    public Integer getViews(Integer courseId) {
        QueryWrapper<Views> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Views views = viewsMapper.selectOne(queryWrapper);
        if (views == null){
            return 0;
        }
        return views.getView();
    }

    @Override
    public Long getAllViews() {
        List<Views> views = viewsMapper.selectList(null);
        Long allViews = 0l;
        for (Views v :
                views) {
            allViews += v.getView();
        }
        return allViews;
    }
}
