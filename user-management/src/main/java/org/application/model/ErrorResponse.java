package org.application.model;

import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String status;
    private Map<String,Object> errors;
}

//ErrorResponse ErrorResponse=new ErrorResponse();

//ErrorResponse.builder().message("der").build();