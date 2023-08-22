package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.request.CreatePensionRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.service.PensionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/mpb/portal")
public class PensionerController {

    @Autowired
    PensionerService pensionerService;

    @PostMapping("/pensioners")
    public ResponseEntity<BaseResponse> createPensioner(@RequestBody List<CreatePensionRequest> createPensionRequest, HttpServletRequest request) {
        BaseResponse response = pensionerService.createPensioner(createPensionRequest,request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/pensioners/{id}")
    public ResponseEntity<BaseResponse> updatePensioner(@PathVariable long id,@RequestBody CreatePensionRequest createPensionRequest, HttpServletRequest request) {
        BaseResponse response = pensionerService.updatePensioner(id,createPensionRequest,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pensioners")
    public ResponseEntity<BaseResponse> queryPensioner(@RequestParam(required = false) String serviceNo,
                                                  @RequestParam(required = false) String rank,
                                                       @RequestParam(required = false) String lastName,
                                                       @RequestParam(required = false) String firstName,
                                                       @RequestParam(required = false) String otherName,
                                                       @RequestParam(required = false) String email,
                                                       @RequestParam(required = false) String phone,
                                                       @RequestParam(required = false) String bankCode,
                                                       @RequestParam(required = false) String accountNo,
                                                       @RequestParam(required = false) String bvn,
                                                  @RequestParam(required = false) Long id,
                                                  @RequestParam(required = false, defaultValue = "1") int number,
                                                  @RequestParam(required = false, defaultValue = "30") int size,HttpServletRequest request) {
        Pageable paging = PageRequest.of(number - 1 ,size);
        BaseResponse response = pensionerService.getPensioner(serviceNo,rank,lastName,firstName,otherName,email,phone,bankCode,accountNo,bvn,id,paging);
        return ResponseEntity.ok(response);
    }
}
