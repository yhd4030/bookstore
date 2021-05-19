package com.bookstore.haid;

import com.bookstore.haid.dto.OrderInfoDTO;
import com.bookstore.haid.service.OrderService;
import com.bookstore.haid.utils.PasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HaidApplicationTests {
    @Autowired
    private OrderService orderService;
    @Test
    void contextLoads() {

    }



}
