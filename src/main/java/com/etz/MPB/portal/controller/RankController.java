package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.request.CreateBankRequest;
import com.etz.MPB.portal.dto.request.CreateRankRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.entity.Users;
import com.etz.MPB.portal.repositories.UserRepository;
import com.etz.MPB.portal.service.BankService;
import com.etz.MPB.portal.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/apis/mpb/portal")
public class RankController {

    @Autowired
    RankService rankService;

    @PostMapping("/ranks")
    public ResponseEntity<BaseResponse> createBank(@RequestBody CreateRankRequest createRankRequest) {
        BaseResponse response = rankService.createRanks(createRankRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/ranks/{id}")
    public ResponseEntity<BaseResponse> updateBank(@PathVariable long id,@RequestBody CreateRankRequest createRankRequest) {
        BaseResponse response = rankService.updateRanks(id,createRankRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ranks")
    public ResponseEntity<BaseResponse> queryBank(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String code,
                                                  @RequestParam() Long id,
                                                  @RequestParam(required = false, defaultValue = "1") int number,
                                                  @RequestParam(required = false, defaultValue = "30") int size) {
        Pageable paging = PageRequest.of(number - 1,size);
        BaseResponse response = rankService.getRanks(name,code,id,paging);
        return ResponseEntity.ok(response);
    }
}
