package com.thoughtworks.bh.api.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {
    private boolean success;
    private String message;
    private T result;


    public static CommonResponse fail(String message) {
        return CommonResponse.builder().message(message).success(false).build();
    }

    public static <T> CommonResponse success(String message) {
        return CommonResponse.builder()
                .message(message)
                .success(true)
                .build();
    }

    public static <T> CommonResponse success(String message, T data) {
        return CommonResponse.builder()
                .message(message)
                .success(true)
                .result(data)
                .build();
    }
}