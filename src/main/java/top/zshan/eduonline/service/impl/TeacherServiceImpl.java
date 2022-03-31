package top.zshan.eduonline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zshan.eduonline.bean.Teacher;
import top.zshan.eduonline.bean.User;
import top.zshan.eduonline.mapper.TeacherMapper;
import top.zshan.eduonline.service.TeacherService;

import java.util.List;

/**
 * @author SansZhu
 * @create 2022/3/29 22:44
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = teacherMapper.selectList(null);
        return teachers;
    }

    @Override
    public boolean insertTeacherForOne(Teacher teacher) {
        int insert = teacherMapper.insert(teacher);
        if (insert == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTeacher(Integer teachId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_id",teachId);
        int delete = teacherMapper.delete(queryWrapper);
        if (delete == 1){
            return true;
        }else
            return false;

    }

    @Override
    public Teacher getTeacherForOne(Integer teachId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_id",teachId);
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public boolean updateTeacher(Integer teachId, String teachName, String teachPhone, String teachEmail) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_id",teachId);
        int update = teacherMapper.update(new Teacher(teachName, teachEmail, teachPhone), queryWrapper);
        if (update == 1){
            return true;
        }else
            return false;
    }

    @Override
    public boolean updatePhoto(Integer teachId, String photoUrl) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_id",teachId);
        int update = teacherMapper.update(new Teacher(photoUrl), queryWrapper);
        if (update == 1){
            return true;
        }else
            return false;
    }

    @Override
    public String selectTeacherName(Integer teachId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teach_id",teachId);
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        String teachName = teacher.getTeachName();
        return teachName;
    }
}
