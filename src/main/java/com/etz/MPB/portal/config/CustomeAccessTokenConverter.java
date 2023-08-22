package com.etz.MPB.portal.config;


import com.etz.MPB.portal.enums.PropertyConfig;
import com.etz.MPB.portal.entity.Users;
import com.etz.MPB.portal.repositories.PropertyConfigRepository;
import com.etz.MPB.portal.repositories.UserRepository;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class CustomeAccessTokenConverter extends JwtAccessTokenConverter {

    private final UserRepository userRepository;

//    @Autowired
//    PropertyConfigRepository propertyConfigRepository;

    public CustomeAccessTokenConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        Optional<Users> optUser = userRepository.findByUsername(user.getUsername());

        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException("The username cannot be found");
        }


        Set<CustomPermission> authorities = new HashSet<>();
        optUser.get().getRoles().stream().forEach(r -> {
            r.getPermissions().forEach(permissions -> {
                CustomPermission customPermission = new CustomPermission();
                customPermission.setId(permissions.getId());
                customPermission.setName(permissions.getName());
                authorities.add(customPermission);});
        });

       Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());


        info.put("firstname",optUser.get().getFirstName());
        info.put("email",optUser.get().getEmail());
        info.put("mobile",optUser.get().getPhone());
        info.put("lastname",optUser.get().getLastName());


//        Optional<com.etz.MPB.portal.entity.PropertyConfig> sessionTimeout = propertyConfigRepository.findById((long) PropertyConfig.SESSION_TIME_OUT.ordinal());
//        info.put("session_timeout",Long.parseLong(sessionTimeout.get().getValue()));


        info.put("roles", optUser.get().getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()));
        info.put("authorities", authorities.stream().collect(Collectors.toList()));
        DefaultOAuth2AccessToken customeAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customeAccessToken.setAdditionalInformation(info);

        return super.enhance(customeAccessToken, authentication);
    }
}
