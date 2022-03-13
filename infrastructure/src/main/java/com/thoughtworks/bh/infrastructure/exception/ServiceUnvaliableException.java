package com.thoughtworks.bh.infrastructure.exception;

import com.thoughtworks.bh.domain.common.BaseException;

public class ServiceUnvaliableException extends BaseException {

    public ServiceUnvaliableException(String message) {
        super(503, message);
    }
}
