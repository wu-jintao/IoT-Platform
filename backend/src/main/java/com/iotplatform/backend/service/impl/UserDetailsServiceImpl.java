package com.iotplatform.backend.service.impl;

import com.iotplatform.backend.dao.UserMapper;
import com.iotplatform.backend.pojo.User;
import com.iotplatform.backend.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findForLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
