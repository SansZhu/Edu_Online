package top.zshan.eduonline;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author SansZhu
 * @create 2022/4/7 22:26
 */
public class PasswordEncryption {
    @Test
    public void Test(){
        byte[] password = "123123".getBytes(StandardCharsets.UTF_8);

        String s = DigestUtils.md5DigestAsHex(password);
        System.out.println(s);

    }
}
