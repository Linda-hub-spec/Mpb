package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.request.UserReq;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.dto.response.ResponseConstants;
import com.etz.MPB.portal.entity.Roles;
import com.etz.MPB.portal.entity.Users;
import com.etz.MPB.portal.enums.UserStatus;
import com.etz.MPB.portal.exceptions.Exception;
import com.etz.MPB.portal.repositories.RoleRepository;
import com.etz.MPB.portal.repositories.UserRepository;
import com.etz.MPB.portal.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper mapper;


    @Override
    public BaseResponse createUser(UserReq userReq) throws Exception {
        Long startTime = System.currentTimeMillis();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        BaseResponse<Object> response = new BaseResponse<>();


        try {
            if (userRepository.existsByEmail(userReq.getEmail())) {
                throw new Exception("Email already exist");
            }

            if (userRepository.existsByUsername(userReq.getUsername())) {
                throw new Exception("Username already exist");
            }

            Users user = mapper.map(userReq, Users.class);
            user.setCypher("{bcrypt}" + bCryptPasswordEncoder.encode(userReq.getPassword()));
            user.setCreatedBy(0L);
            user.setCreatedOn(LocalDateTime.now());
            user.setStatus(UserStatus.ENABLED);
            user.setAuthorized(true);
            Set<Roles> rolesSet = new HashSet<>();

            if(!userReq.getRoles().isEmpty()){
                Set<Roles> finalRolesSet = rolesSet;
                userReq.getRoles().stream().forEach(role ->{
                    Optional<Roles> optRole = roleRepository.findById(role);
                    finalRolesSet.add(optRole.get());
                });
            }

            user.setRoles(rolesSet);
            userRepository.save(user);
            Long endTime = System.currentTimeMillis();
            response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
            response.setResponseMessage("User has been created successfully");
            response.setExecTime(endTime - startTime);
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public BaseResponse updateUser(Long id, UserReq userReq) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();
        try {
            Users user = userRepository.getById(id);
            user.setFirstName(userReq.getFirstName());
            user.setFirstName(userReq.getFirstName());
            user.setPhone(userReq.getPhone());
            user.setUpdatedBy(0L);
            user.setUpdatedOn(LocalDateTime.now());
            user.setStatus(userReq.getStatus());
            user.setAuthorized(true);
            Set<Roles> rolesSet = new HashSet<>();
            if(!userReq.getRoles().isEmpty()){
                Set<Roles> finalRolesSet = rolesSet;
                userReq.getRoles().stream().forEach(role ->{
                    Optional<Roles> optRole = roleRepository.findById(role);
                    finalRolesSet.add(optRole.get());
                });
            }

            user.setRoles(rolesSet);
            userRepository.save(user);
            Long endTime = System.currentTimeMillis();
            response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
            response.setResponseMessage("User has been updated successfully");
            response.setExecTime(endTime - startTime);
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public BaseResponse queryUser(int number, int size) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        Pageable paging = PageRequest.of(number - 1,size);
        Page<Users> usersPage = userRepository.queryUser(paging);
        data.put("content", usersPage.getContent());
        data.put("page", usersPage.getNumber() + 1);
        data.put("size", usersPage.getSize());
        data.put("totalElements", usersPage.getTotalElements());
        data.put("totalPages", usersPage.getTotalPages());
        data.put("first", usersPage.isFirst());
        data.put("last", usersPage.isLast());

        Long endTime = System.currentTimeMillis();
        response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        response.setResponseMessage("Successfully processed");
        response.setExecTime(endTime - startTime);
        response.setData(data);
        return response;
    }
}
