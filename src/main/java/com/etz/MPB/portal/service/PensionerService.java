package com.etz.MPB.portal.service;

import com.etz.MPB.portal.dto.request.CreatePensionRequest;
import com.etz.MPB.portal.dto.request.UpdatePensionerRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.enums.PensionerStatus;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface PensionerService {

    BaseResponse createPensioner(List<CreatePensionRequest> createPensionRequest, HttpServletRequest request);

    BaseResponse updatePensioner(long id, UpdatePensionerRequest updatePensionRequest, HttpServletRequest request);

    BaseResponse getPensioner(Boolean authorised, Date createdDateFrom, Date createdDateTo, String name,  String email,
                              String phone, PensionerStatus status, Long id, Pageable paging);

}
