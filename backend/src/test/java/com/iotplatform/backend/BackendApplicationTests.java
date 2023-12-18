package com.iotplatform.backend;

import com.iotplatform.backend.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void testSaveUser() {
        User user=new User();
        user.setId(1);
        user.setPassword("123456");
        user.setDateJoined(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setUsername("赵一");
        user.setEmail("zhaoyi@163.com");
        mongoTemplate.save(user);
    }
}
