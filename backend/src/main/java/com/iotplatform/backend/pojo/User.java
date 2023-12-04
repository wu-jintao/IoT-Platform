package com.iotplatform.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String id;
    private String password;
    private LocalDateTime lastLogin;
    private String username;
    private String email;
    private Short isActive;
    private LocalDateTime dateJoined;
    private Short isSuperuser;
    private Short isStaff;
    private Short isDeveloper;
    private String img;
    private String phone;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

}