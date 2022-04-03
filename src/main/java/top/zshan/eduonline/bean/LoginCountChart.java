package top.zshan.eduonline.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author SansZhu
 * @create 2022/4/1 23:07
 */
@Data
public class LoginCountChart {
    private String elapsed;
    private Integer value;

    public LoginCountChart(String elapsed, Integer value) {
        this.elapsed = elapsed;
        this.value = value;
    }
}
