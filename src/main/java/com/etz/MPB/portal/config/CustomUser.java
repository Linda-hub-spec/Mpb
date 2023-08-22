package com.etz.MPB.portal.config;

import com.etz.MPB.portal.entity.Users;
import com.etz.MPB.portal.enums.ConstantStatus;
import com.etz.MPB.portal.enums.ResponseEnum;
import com.etz.MPB.portal.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUser implements UserDetails {
    Users users;

    public CustomUser(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        users.getRoles().forEach(role -> {
            role.getPermissions().forEach(permissions -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permissions.getName()));
            });
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return users.getCypher();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //users.isCredentialsNonExpired();}
    }

    @Override
    public boolean isEnabled() {
        if(users.getStatus() == UserStatus.ENABLED){
            return true;
        }
        return false;
    }
}
