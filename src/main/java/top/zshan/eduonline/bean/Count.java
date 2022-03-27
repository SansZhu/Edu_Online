package top.zshan.eduonline.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author SansZhu
 * @create 2022/3/27 22:17
 */
@Data
public class Count {
    private Date date;
    private Integer loginCount;
}
