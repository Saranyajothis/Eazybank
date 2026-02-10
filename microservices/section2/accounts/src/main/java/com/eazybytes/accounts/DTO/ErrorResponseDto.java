package com.eazybytes.accounts.DTO;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema (name = "Error Response Data Transfer Object", 
    description = "Error Response Data Transfer Object for managing error response details"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {
    
    @Schema(description = "API path where the error occurred", example = "/api/accounts/12345")
    private String apiPath;

    @Schema(description = "HTTP status code of the error response", example = "404")
    private HttpStatus errorCode;
    
    @Schema(description = "Detailed error message", example = "Account not found for account number 12345")
    private String errorMessage;
    
    @Schema(description = "Timestamp when the error occurred", example = "2024-06-15T14:30:00")
    private LocalDateTime errorTime;
}
