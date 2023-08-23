package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.response.*;
import com.etz.MPB.portal.entity.*;
import com.etz.MPB.portal.enums.VerificationStatus;
import com.etz.MPB.portal.repositories.*;
import com.etz.MPB.portal.service.ReferenceVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ReferenceVerificationServiceImpl implements ReferenceVerificationService {
    @Autowired
    ValidReferencesRepository validReferencesRepository;
    @Autowired
    PensionerRepository pensionerRepository;
    @Autowired
    VerificationPeriodsRepository verificationPeriodsRepository;
    @Autowired
    VerificationRepository verificationRepository;

    @Autowired
    ValidVerificationRepository validVerificationRepository;
    @Autowired
    PensionerDocumentRepository pensionerDocumentRepository;

    @Override
    public BaseResponse verifyReference(String reference) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();
        ReferenceVerificationResponse verificationResponse = new ReferenceVerificationResponse();

        //check reference from valid reference table and get details
        Optional<ValidReferences> referencesOptional = validReferencesRepository.findByReference(reference);
        if(!referencesOptional.isPresent()){
            response.setResponseCode(String.valueOf(ResponseConstants.INVALID_CODE));
            response.setResponseMessage("Reference does not Exist");
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }

        ValidReferences references = referencesOptional.get();
        verificationResponse.setId(references.getId());
        verificationResponse.setReference(reference);

        //get pensioner id and get details from pensioner table
       Optional<Pensioners> pensionersOptional = pensionerRepository.findById(references.getPensionerId());
        if(!pensionersOptional.isPresent()){
            response.setResponseCode(String.valueOf(ResponseConstants.INVALID_CODE));
            response.setResponseMessage("Pensioner does not Exist");
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }

        Pensioners pensioners = pensionersOptional.get();
        PensionerVerify pensionerVerify = new PensionerVerify();
        pensionerVerify.setBvn(pensioners.getBvn());
        pensionerVerify.setFirstName(pensioners.getFirstName());
        pensionerVerify.setLastName(pensioners.getLastName());
        pensionerVerify.setOtherName(pensioners.getOtherName());
        pensionerVerify.setEmail(pensioners.getEmail());
        pensionerVerify.setPhone(pensioners.getPhone());
        pensionerVerify.setBvn(pensioners.getBvn());
        pensionerVerify.setVerificationStatus(pensioners.getVerificationStatus().name());
        pensionerVerify.setVerificationOption(pensioners.getVerificationOption().name());
        pensionerVerify.setCreatedBy(pensioners.getCreatedBy());
        pensionerVerify.setCreatedOn(pensioners.getCreatedOn());
        pensionerVerify.setUpdatedBy(pensioners.getUpdatedBy());
        pensionerVerify.setUpdatedOn(pensioners.getUpdatedOn());


        //use verificationPeriodId get start and end date from verification period table
        Optional<VerificationPeriods> verificationPeriodsOptional=verificationPeriodsRepository.findById(references.getVerificationPeriodId());
        if(!verificationPeriodsOptional.isPresent()){
            response.setResponseCode(String.valueOf(ResponseConstants.INVALID_CODE));
            response.setResponseMessage("Verification Period does not Exist");
            response.setExecTime(System.currentTimeMillis() - startTime);
            return response;
        }
        VerificationPeriods verificationPeriods = verificationPeriodsOptional.get();

        // get qrcode from pensioner-document is between (use verificationPeriodId get start and end date)
        String qrcode = pensionerDocumentRepository.findUrl(verificationPeriods.getStartDate(),verificationPeriods.getEndDate(),references.getPensionerId());
        verificationResponse.setQrCode(qrcode);

        //get verificationdetails from verification table where
       List<Verifications> verificationsList = verificationRepository.findByPensionerId(references.getPensionerId());
       List<VerificationDetails> verificationDetailsList = new ArrayList<>();
       verificationsList.stream().forEach(verifications -> {
           VerificationDetails verificationDetails = new VerificationDetails();
           verificationDetails.setVerificationType(verifications.getType().name());
           verificationDetails.setDegree(verifications.getDegree());
           verificationDetails.setFirstData(verifications.getFirstData());
           verificationDetails.setSecondData(verifications.getSecondData());
           verificationDetails.setCreatedOn(verifications.getCreatedOn());

           ValidVerifications validVerifications = validVerificationRepository.findByVerificationId(verifications.getId());
           verificationDetails.setMatch(validVerifications.isMatch());
           verificationDetailsList.add(verificationDetails);
       });
        pensionerVerify.setVerificationDetails(verificationDetailsList);
        verificationResponse.setPensioner(pensionerVerify);

        response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        response.setResponseMessage("Reference retrieved successfully");
        response.setData(verificationResponse);
        response.setExecTime(System.currentTimeMillis() - startTime);
        return response;
    }
}
