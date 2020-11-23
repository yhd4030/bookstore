package com.bookstore.haid;

import com.bookstore.haid.utils.PasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HaidApplicationTests {

    @Test
    void contextLoads() {
        String s = PasswordEncoderUtil.passwordEncoder("$2a$10$QZvtajDD3blJXGvf4kA3kealdD13N4eX/5N4bR9sE4WJYk/c2MI5W");
        System.out.println(s);
    }



}
