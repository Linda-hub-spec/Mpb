package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.request.CreatePensionRequest;
import com.etz.MPB.portal.dto.request.UpdatePensionerRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.dto.response.ResponseConstants;
import com.etz.MPB.portal.entity.Pensioners;
import com.etz.MPB.portal.entity.Ranks;
import com.etz.MPB.portal.entity.Users;
import com.etz.MPB.portal.enums.PensionerStatus;
import com.etz.MPB.portal.enums.ResponseEnum;
import com.etz.MPB.portal.enums.VerificationStatus;
import com.etz.MPB.portal.repositories.PensionerRepository;
import com.etz.MPB.portal.repositories.UserRepository;
import com.etz.MPB.portal.service.PensionerService;
import com.etz.MPB.portal.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PensionerServiceImpl implements PensionerService {

    @Autowired
    PensionerRepository pensionerRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse createPensioner(List<CreatePensionRequest> createPensionRequest, HttpServletRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();
        Map<String, Object> loggedInUser = SecurityUtil.getLoggedInUser(request);
        String tokenUserType = (String) loggedInUser.get("username");

        Optional<Users> users = userRepository.findByUsername(tokenUserType);
        Date createdDate = new Date();
        createPensionRequest.stream().forEach(pension ->{
            Pensioners pensioners = new Pensioners();
            pensioners.setServiceNo(pension.getServiceNo());
            pensioners.setRank(pension.getRank());
            pensioners.setLastName(pension.getLastName());
            pensioners.setFirstName(pension.getFirstName());
            pensioners.setOtherName(pension.getOtherName());
            pensioners.setEmail(pension.getEmail());
            pensioners.setPhone(pension.getPhone());
            pensioners.setBankCode(pension.getBankCode());
            pensioners.setAccountNo(pension.getAccountNo());
            pensioners.setBvn(pension.getBvn());
            pensioners.setVerificationStatus(VerificationStatus.VERIFICATION_NOT_COMPLETED);
            pensioners.setStatus(PensionerStatus.PENDING_CREATION_AUTHORIZATION);
            pensioners.setCreatedBy(users.get().getId());
            pensioners.setCreatedOn(createdDate);

            pensionerRepository.save(pensioners);

        });

        response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        response.setResponseMessage("Pensioner(s) has been created successfully");
        response.setExecTime(System.currentTimeMillis() - startTime);
        return response;

    }

    @Override
    public BaseResponse updatePensioner(long id, UpdatePensionerRequest updatePensionRequest, HttpServletRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();

        Map<String, Object> loggedInUser = SecurityUtil.getLoggedInUser(request);
        String tokenUserType = (String) loggedInUser.get("username");
        Optional<Users> users = userRepository.findByUsername(tokenUserType);
        Date createdDate = new Date();

        Pensioners pensioners = pensionerRepository.getById(id);
        pensioners.setServiceNo(updatePensionRequest.getServiceNo()==null?pensioners.getServiceNo():updatePensionRequest.getServiceNo());
        pensioners.setRank(updatePensionRequest.getRank()==null?pensioners.getRank():updatePensionRequest.getRank());
        pensioners.setLastName(updatePensionRequest.getLastName()==null?pensioners.getLastName():updatePensionRequest.getLastName());
        pensioners.setFirstName(updatePensionRequest.getFirstName()==null? pensioners.getFirstName() : updatePensionRequest.getFirstName());
        pensioners.setOtherName(updatePensionRequest.getOtherName()==null?pensioners.getOtherName():updatePensionRequest.getOtherName());
        pensioners.setEmail(updatePensionRequest.getEmail()==null? pensioners.getEmail() : updatePensionRequest.getEmail());
        pensioners.setPhone(updatePensionRequest.getPhone()==null?pensioners.getPhone():updatePensionRequest.getPhone());
        pensioners.setBankCode(updatePensionRequest.getBankCode()==null?pensioners.getBankCode():updatePensionRequest.getBankCode());
        pensioners.setAccountNo(updatePensionRequest.getAccountNo()==null?pensioners.getAccountNo():updatePensionRequest.getAccountNo());
        pensioners.setBvn(updatePensionRequest.getBvn()==null?pensioners.getBvn():updatePensionRequest.getBvn());
        pensioners.setVerificationStatus(updatePensionRequest.getVerificationStatus() == null?pensioners.getVerificationStatus():updatePensionRequest.getVerificationStatus());
        pensioners.setStatus(updatePensionRequest.getPensionerStatus()==null?pensioners.getStatus():updatePensionRequest.getPensionerStatus());
        pensioners.setUpdatedBy(users.get().getId());
        pensioners.setUpdatedOn(createdDate);

        pensionerRepository.save(pensioners);

        response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        response.setResponseMessage("Pensioner has been updated successfully");
        response.setExecTime(System.currentTimeMillis() - startTime);
        return response;
    }

    @Override
    public BaseResponse getPensioner(Boolean authorised, Date createdDateFrom, Date createdDateTo, String name,  String email,
                                     String phone, PensionerStatus status, Long id, Pageable paging) {
        Page<Pensioners> ranksList = pensionerRepository.findPensioner(authorised,createdDateFrom,createdDateTo,name,email,phone,status,id,paging);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Banks fetched successfully.");
        baseResponse.setData(ranksList);
        return baseResponse;

    }
}
