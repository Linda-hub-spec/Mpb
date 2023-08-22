package com.etz.MPB.portal.service;

import com.etz.MPB.portal.dto.request.CreateBankRequest;
import com.etz.MPB.portal.dto.request.CreateRankRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import org.springframework.data.domain.Pageable;

public interface RankService {
    BaseResponse createRanks(CreateRankRequest createRankRequest);
    BaseResponse updateRanks(long id,CreateRankRequest createRankRequest);
    BaseResponse getRanks(String name, String code, Long id , Pageable paging);
}
