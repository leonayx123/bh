package com.thoughtworks.bh.api.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.bh.api.common.CommonResponse;
import com.thoughtworks.bh.domain.common.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice("com.thoughtwors")
@RequiredArgsConstructor
public class ExceptionHandle {
   private final ObjectMapper objectMapper ;

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> handBaseException(BaseException e) {
        log.error(" BaseException error:{}", e);
        return new ResponseEntity<>(getJsonResponse(e), HttpStatus.valueOf(e.getCode()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> handBaseException(Exception e) {
        log.error(" Exception error:{}", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
    private String getJsonResponse(BaseException e){
        try {
            return objectMapper.writeValueAsString(CommonResponse.fail( e.getMessage())) ;
        } catch (JsonProcessingException ex) {
            return "";
        }
    }

}