package com.etz.MPB.portal.service;

import com.etz.MPB.portal.dto.request.CreateBankRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import org.springframework.data.domain.Pageable;

public interface BankService {
    BaseResponse createBanks(CreateBankRequest createBankRequest);
    BaseResponse updateBanks(long id,CreateBankRequest createBankRequest);
    BaseResponse getBanks(String name, String code, Long id, Pageable paging);
}
