package top.zshan.eduonline.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.eduonline.service.FileService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author SansZhu
 * @create 2022/3/28 23:03
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String saveFileUser(MultipartFile photo) {
        String originalFilename = photo.getOriginalFilename();
        String fixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(fixName);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = uuid +fixName;
        String photo1 = "A:\\Edu_Online\\resource\\userimg";
        String filePath = photo1+File.separatorChar +fileName;

        try {
            photo.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;

    }
    @Override
    public String saveFileTeacher(MultipartFile photo) {
        String originalFilename = photo.getOriginalFilename();
        String fixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(fixName);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = uuid +fixName;
        String photo1 = "A:\\Edu_Online\\resource\\teachimg";
        String filePath = photo1+File.separatorChar +fileName;

        try {
            photo.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;

    }

    @Override
    public String saveFileCourse(MultipartFile photo) {
        String originalFilename = photo.getOriginalFilename();
        String fixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(fixName);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = uuid +fixName;
        String photo1 = "A:\\Edu_Online\\resource\\courseimg";
        String filePath = photo1+File.separatorChar +fileName;
        try {
            photo.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public String saveFileVideo(MultipartFile video) {
        String originalFilename = video.getOriginalFilename();
        String fixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(fixName);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String fileName = uuid +fixName;
        String photo1 = "A:\\Edu_Online\\resource\\coursevideo";
        String filePath = photo1+File.separatorChar +fileName;
        try {
            video.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
