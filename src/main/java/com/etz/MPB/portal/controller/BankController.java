package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.request.CreateBankRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.repositories.BankRepository;
import com.etz.MPB.portal.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    @Autowired
    BankService bankService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> createBank(@RequestBody CreateBankRequest createBankRequest) {
        BaseResponse response = bankService.createBanks(createBankRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBank(@PathVariable long id,@RequestBody CreateBankRequest createBankRequest) {
        BaseResponse response = bankService.updateBanks(id,createBankRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<BaseResponse> queryBank(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String code,
                                                  @RequestParam(required = false) Long id,
                                                  @RequestParam(required = false, defaultValue = "1") int number,
                                                  @RequestParam(required = false, defaultValue = "30") int size) {
        Pageable paging = PageRequest.of(number - 1 ,size);
        BaseResponse response = bankService.getBanks(name,code,id,paging);
        return ResponseEntity.ok(response);
    }

}
