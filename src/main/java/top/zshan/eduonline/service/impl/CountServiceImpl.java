package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.Count;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.mapper.CountMapper;
import top.zshan.eduonline.service.CountService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author SansZhu
 * @create 2022/4/1 22:20
 */
@Service
public class CountServiceImpl implements CountService {
    @Autowired
    CountMapper countMapper;

//    记录登录数
    @Override
    public void setCountDaily() {
        Date date = new Date();
        long time = date.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(time);
        QueryWrapper<Count> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date",format);
        Count count = countMapper.selectOne(queryWrapper);
        if (count == null){
            countMapper.insert(new Count(new java.sql.Date(time),1));
        }else {
            Integer loginCount = count.getLoginCount();
            Integer num = ++loginCount;
            countMapper.update(new Count(num),queryWrapper);
        }

    }

    @Override
    public List<Count> getAllCount() {
        List<Count> counts = countMapper.selectList(null);
        return counts;
    }

    @Override
    public List<Count> getAllCountToChart() {
        QueryWrapper<Count> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        queryWrapper.last("limit 0,6");
        List<Count> courses = countMapper.selectList(queryWrapper);
        Collections.reverse(courses);
        return courses;
    }

    @Override
    public Long getAllCountNum() {
        List<Count> counts = countMapper.selectList(null);
        Long countNum = 0l;
        for (Count c :
                counts) {
           countNum += c.getLoginCount();
        }
        return countNum;
    }
}
