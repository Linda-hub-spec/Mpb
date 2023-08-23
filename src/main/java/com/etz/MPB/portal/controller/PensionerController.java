package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.request.CreatePensionRequest;
import com.etz.MPB.portal.dto.request.UpdatePensionerRequest;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.enums.PensionerStatus;
import com.etz.MPB.portal.service.PensionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pensioners")
public class PensionerController {

    @Autowired
    PensionerService pensionerService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse> createPensioner(@RequestBody List<CreatePensionRequest> createPensionRequest, HttpServletRequest request) {
        BaseResponse response = pensionerService.createPensioner(createPensionRequest,request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updatePensioner(@PathVariable long id, @RequestBody UpdatePensionerRequest updatePensionRequest, HttpServletRequest request) {
        BaseResponse response = pensionerService.updatePensioner(id,updatePensionRequest,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<BaseResponse> queryPensioner(@RequestParam(required = false) Boolean authorised,
                                                       @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) Date createdDateFrom,
                                                       @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam(required = false) Date createdDateTo,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String email,
                                                       @RequestParam(required = false) String phone,
                                                       @RequestParam(required = false) PensionerStatus status,
                                                  @RequestParam(required = false) Long id,
                                                  @RequestParam(required = false, defaultValue = "1") int number,
                                                  @RequestParam(required = false, defaultValue = "30") int size) {
        Pageable paging = PageRequest.of(number - 1 ,size);
        BaseResponse response = pensionerService.getPensioner(authorised,createdDateFrom,createdDateTo,name,email,phone,status,id,paging);
        return ResponseEntity.ok(response);
    }
}
