package top.zshan.eduonline.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author SansZhu
 * @create 2022/3/28 23:03
 */
public interface FileService {

    String saveFileUser(MultipartFile photo);
    String saveFileTeacher(MultipartFile photo);
    String saveFileCourse(MultipartFile photo);
    String saveFileVideo(MultipartFile video);
}
