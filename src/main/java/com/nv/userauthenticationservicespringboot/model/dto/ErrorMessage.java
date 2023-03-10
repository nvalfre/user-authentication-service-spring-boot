package com.nv.userauthenticationservicespringboot.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private final Integer status;
    private final String message;
}
