package com.etz.MPB.portal.service;


import com.etz.MPB.portal.dto.response.BaseResponse;



public interface ReferenceVerificationService {
    BaseResponse verifyReference(String reference);
}
