package com.etz.MPB.portal.service;

import com.etz.MPB.portal.dto.request.CreatePensionRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import org.springframework.data.domain.Pageable;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PensionerService {

    BaseResponse createPensioner(List<CreatePensionRequest> createPensionRequest, HttpServletRequest request);

    BaseResponse updatePensioner(long id,CreatePensionRequest createPensionRequest, HttpServletRequest request);

    BaseResponse getPensioner(String serviceNo, String rank, String lastName, String firstName, String otherName, String email,
                              String phone, String bankCode, String accountNo, String bvn, Long id, Pageable paging);

}
