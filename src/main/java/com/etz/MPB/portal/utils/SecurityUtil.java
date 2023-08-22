package com.etz.MPB.portal.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class SecurityUtil {

    public static Map<String, Object> getLoggedInUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String[] details = token.split("[.]",0);
        byte[] decodeByte = Base64.getDecoder().decode(details[1]);
        String decodeValue = new String(decodeByte, StandardCharsets.UTF_8);
        return stringToObject(decodeValue);
    }

    public static Map<String, Object> stringToObject(String decodeValue){
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonObject = null ;
        try {
            jsonObject = mapper.readValue(decodeValue, typeRef);
        } catch (Exception e) {
            System.out.println("Three might be some issue with the JSON string");
        }
        return jsonObject;
    }
}
