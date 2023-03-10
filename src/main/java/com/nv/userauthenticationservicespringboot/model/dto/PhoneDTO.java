package com.nv.userauthenticationservicespringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private long number;
    private int citycode;
    private String contrycode;
}
