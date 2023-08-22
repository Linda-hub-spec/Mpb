package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.service.ReferenceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mpb/portal")
public class ReferenceVerificationController {

    @Autowired
    ReferenceVerificationService referenceVerificationService;

    @GetMapping("/{reference}")
    public ResponseEntity<BaseResponse> verifyReference(@PathVariable String reference){
        BaseResponse response = referenceVerificationService.verifyReference(reference);
        return ResponseEntity.ok(response);
    }
}
