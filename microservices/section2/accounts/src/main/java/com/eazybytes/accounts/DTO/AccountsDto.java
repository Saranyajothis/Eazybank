package com.eazybytes.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(name = "Accounts Data Transfer Object", 
    description = "Accounts Data Transfer Object for managing account details"
)
@Getter@Setter
public class AccountsDto {

    @Schema(description = "Unique account number", example = "1234567890")
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be numeric")
    private Long accountNumber;
    
    @Schema(description = "Type of the account", example = "Savings")
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(description = "Branch address of the account", example = "123 Main St, Anytown, USA")
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
