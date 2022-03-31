package top.zshan.eduonline.service;

import top.zshan.eduonline.bean.Teacher;


import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/29 22:44
 */
public interface TeacherService {
    List<Teacher> getAllTeacher();
    boolean insertTeacherForOne(Teacher teacher);
    boolean deleteTeacher(Integer teachId);
    Teacher getTeacherForOne(Integer teachId);
    boolean updateTeacher(Integer teachId, String teachName,String teachPhone,String teachEmail);
    boolean updatePhoto(Integer teachId, String photoUrl);
    String selectTeacherName(Integer teachId);
}
