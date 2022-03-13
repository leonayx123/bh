package com.thoughtworks.bh.infrastructure.exception;

import com.thoughtworks.bh.domain.common.BaseException;

public class ReservedException extends BaseException {

    public ReservedException(String message) {
        super(409, message);
    }
}
