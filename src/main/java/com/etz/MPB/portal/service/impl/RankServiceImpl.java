package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.request.CreateRankRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.entity.Ranks;
import com.etz.MPB.portal.enums.ResponseEnum;
import com.etz.MPB.portal.repositories.RankRepository;
import com.etz.MPB.portal.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository rankRepository;

    @Override
    public BaseResponse createRanks(CreateRankRequest createRankRequest) {
        Ranks ranks = new Ranks();
        ranks.setCode(createRankRequest.getCode());
        ranks.setName(createRankRequest.getName());
        rankRepository.save(ranks);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Ranks created successfully.");

        return baseResponse;
    }

    @Override
    public BaseResponse updateRanks(long id,CreateRankRequest createRankRequest) {
        Ranks ranks = rankRepository.getById(id);
        ranks.setCode(createRankRequest.getCode() == null? ranks.getCode():createRankRequest.getCode() );
        ranks.setName(createRankRequest.getName() == null? ranks.getName() : createRankRequest.getName());
        rankRepository.save(ranks);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Ranks updated successfully.");

        return baseResponse;
    }

    @Override
    public BaseResponse getRanks(String name, String code, Long id, Pageable paging) {
        Page<Ranks> ranksList = rankRepository.findRanks(id,name,code,paging);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(ResponseEnum.SUCCESSFUL.getResponseCode());
        baseResponse.setResponseMessage("Ranks fetched successfully.");
        baseResponse.setData(ranksList);
        return baseResponse;
    }
}
