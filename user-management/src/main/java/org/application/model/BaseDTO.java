package org.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    private LocalDateTime createdDateTime;
    private String createdBy;
    private LocalDateTime updatedDateTime;
    private String updatedBy;
}
