package com.mehmetgenc.restaurantservice.general;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestResponse<T>{
    private T data;
    private LocalDateTime responseDate;
    private boolean isSuccess;
    private String message;

    public RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.responseDate = LocalDateTime.now();
        this.isSuccess = isSuccess;
    }

    public RestResponse() {
    }

    public static <T>RestResponse<T> of(T data){
        return new RestResponse<>(data, true);

    }public static <T>RestResponse<T> error(T data){
        return new RestResponse<>(data, false);
    }
}
