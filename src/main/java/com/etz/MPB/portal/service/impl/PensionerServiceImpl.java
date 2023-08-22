package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.request.CreatePensionRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.entity.Pensioners;
import com.etz.MPB.portal.enums.PensionerStatus;
import com.etz.MPB.portal.enums.VerificationStatus;
import com.etz.MPB.portal.service.PensionerService;
import com.etz.MPB.portal.utils.SecurityUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class PensionerServiceImpl implements PensionerService {
    @Override
    public BaseResponse createPensioner(List<CreatePensionRequest> createPensionRequest, HttpServletRequest request) {
        Map<String, Object> loggedInUser = SecurityUtil.getLoggedInUser(request);
        String tokenUserType = (String) loggedInUser.get("user_type");
        createPensionRequest.stream().forEach(pension ->{
            Pensioners pensioners = new Pensioners();
            pensioners.setService_no(pension.getServiceNo());
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

        });
        return null;
    }

    @Override
    public BaseResponse updatePensioner(long id, CreatePensionRequest createPensionRequest, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponse getPensioner(String serviceNo, String rank, String lastName, String firstName, String otherName, String email, String phone, String bankCode, String accountNo, String bvn, Long id, Pageable paging) {
        return null;
    }
}
