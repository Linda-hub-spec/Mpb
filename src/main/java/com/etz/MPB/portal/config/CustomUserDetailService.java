package com.etz.MPB.portal.config;

import com.etz.MPB.portal.entity.Users;


import com.etz.MPB.portal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = userRepository.findByUsername(username);
        Users user = usersOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUser(user);
    }
}
