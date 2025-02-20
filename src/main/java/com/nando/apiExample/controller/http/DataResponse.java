package com.nando.apiExample.controller.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse<T> {
    private String message;
    private T data;
}
