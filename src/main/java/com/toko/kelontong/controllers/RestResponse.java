package com.toko.kelontong.controllers;

import lombok.Data;

@Data
public class RestResponse<D> {

    private final D data;
    private final String message;
    private final Integer status;
}
