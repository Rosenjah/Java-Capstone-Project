package com.capston;

import com.capston.dao.UserDao;
import com.capston.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    @Override
    public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.loadUserByUsername(username);
        List<GrantedAuthority> roles=new ArrayList<>();
        MyUserPrincipal customUserDetails = null;
        if (user != null) {
            customUserDetails = new MyUserPrincipal(username, user.getPassword(), roles);

        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return customUserDetails;
    }
}