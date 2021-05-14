package com.cac.mycontacts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResourceResponse {
    private String errorCode;
    private String errorMessage;
    private Object data;
}
