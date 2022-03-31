package top.zshan.eduonline.service;

import top.zshan.eduonline.bean.Course;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/28 17:46
 */
public interface CourseService {
    List<Course> getAllCourse();
    List<Course> getAllCourseDistinct();
    List<Course> getMainPageCourse();
    boolean insertCourseForOne(Course course);
    Course getCourseForOne(Integer courseId);
    boolean updateCourse(Integer courseId,Integer teachId,String courseType,String detail,String courseName);
    boolean deleteCourse(Integer courseId);
    boolean updatePhoto(Integer courseId, String photoUrl);
}
