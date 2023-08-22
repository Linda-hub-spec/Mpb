package com.etz.MPB.portal.service;

import com.etz.MPB.portal.dto.request.UserReq;
import com.etz.MPB.portal.dto.response.BaseResponse;

public interface UserService {
    BaseResponse createUser(UserReq userReq) throws Exception;

    BaseResponse updateUser(Long id, UserReq userReq);

    BaseResponse queryUser(int number, int size);
}
