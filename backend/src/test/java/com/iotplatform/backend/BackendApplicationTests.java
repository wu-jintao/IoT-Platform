package com.iotplatform.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches("123456", "$2a$10$cy8gCc1UWAwr0PTgTDBYI.f2FtuR1TBM36VMg6L/QkMHf2T2CnPNW"));
        System.out.println(passwordEncoder.encode("w123456"));
    }

}
