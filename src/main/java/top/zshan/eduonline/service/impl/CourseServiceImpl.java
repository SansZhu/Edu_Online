package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.Course;
import top.zshan.eduonline.bean.Teacher;
import top.zshan.eduonline.mapper.CourseMapper;
import top.zshan.eduonline.service.CourseService;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/28 17:47
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper,Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> getAllCourse() {
        List<Course> courses = courseMapper.selectList(null);
        return courses;
    }

    @Override
    public List<Course> getAllCourseDistinct() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT course_type");
        List<Course> courses = courseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public List<Course> getMainPageCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("course_id");
        queryWrapper.last("limit 0,6");
        List<Course> courses = courseMapper.selectList(queryWrapper);

        return courses;
    }

    @Override
    public boolean insertCourseForOne(Course course) {
        int insert = courseMapper.insert(course);
        if (insert == 1){
            return true;
        }
        return false;
    }

    @Override
    public Course getCourseForOne(Integer courseId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Course course = courseMapper.selectOne(queryWrapper);
        return course;
    }

    @Override
    public boolean updateCourse(Integer courseId, Integer teachId, String courseType, String detail, String courseName) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        int update = courseMapper.update(new Course(teachId, courseType, detail, courseName), queryWrapper);
        if (update == 1){
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        int delete = courseMapper.delete(queryWrapper);
        if (delete == 1){
            return true;
        }else
            return false;
    }

    @Override
    public boolean updatePhoto(Integer courseId, String photoUrl) {
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("course_id",courseId);
            int update = courseMapper.update(new Course(photoUrl), queryWrapper);
            if (update == 1){
                return true;
            }else
                return false;
    }

    @Override
    public List<Course> getAllCourseByType(String courseType) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_type",courseType);
        List<Course> courses = courseMapper.selectList(queryWrapper);
        return courses;
    }


}
