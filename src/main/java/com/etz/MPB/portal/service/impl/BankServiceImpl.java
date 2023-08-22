package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.request.CreateBankRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.entity.Banks;
import com.etz.MPB.portal.entity.Ranks;
import com.etz.MPB.portal.enums.ResponseEnum;
import com.etz.MPB.portal.repositories.BankRepository;
import com.etz.MPB.portal.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankRepository bankRepository;

    @Override
    public BaseResponse createBanks(CreateBankRequest createBankRequest) {
        Banks banks = new Banks();
        banks.setCode(createBankRequest.getCode());
        banks.setName(createBankRequest.getName());
        bankRepository.save(banks);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Banks created successfully.");

        return baseResponse;
    }

    @Override
    public BaseResponse updateBanks(long id,CreateBankRequest createBankRequest) {
        Banks banks = bankRepository.getById(id);
        banks.setCode(createBankRequest.getCode() == null? banks.getCode():createBankRequest.getCode() );
        banks.setName(createBankRequest.getName() == null? banks.getName() : createBankRequest.getName());
        bankRepository.save(banks);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Banks updated successfully.");

        return baseResponse;
    }

    @Override
    public BaseResponse getBanks(String name, String code, Long id, Pageable paging) {
        Page<Ranks> ranksList = bankRepository.findBanks(id,name,code,paging);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Banks fetched successfully.");
        baseResponse.setData(ranksList);
        return baseResponse;
    }
}
