package com.eazybytes.accounts.Controller;

import javax.print.attribute.standard.Media;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.DTO.ErrorResponseDto;
import com.eazybytes.accounts.DTO.ResponseDto;
import com.eazybytes.accounts.Service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import com.eazybytes.accounts.Constants.AccountsConstants;


@Tag(
    name = "Accounts API", 
    description = "This API allows you to create, update, fetch and delete customer accounts. " +
            "You can use the following endpoints:\n" +
            "- POST /api/create: Create a new account\n" +
            "- PUT /api/update: Update existing account details\n" +
            "- GET /api/fetch: Fetch account details by mobile number\n" +
            "- DELETE /api/delete: Delete an account by mobile number"
)

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iaccountsService;

    @Operation(summary = "Create a new account", 
        description = "Creates a new customer account with the provided details.")
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iaccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    @Operation(summary = "Update account details", 
        description = "Updates the details of an existing customer account.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account updated successfully"),
        @ApiResponse(responseCode = "417", description = "Exception failed"),
        @ApiResponse(responseCode = "500", description = "Failed to update account",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class))
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iaccountsService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(summary = "Fetch account details", 
        description = "Fetches the account details of a customer based on the provided mobile number.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account details fetched successfully"),
        @ApiResponse(responseCode = "417", description = "Exception failed"),
        @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam 
        @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
        String mobileNumber) {
        CustomerDto customerdto = iaccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerdto);
    }

    @Operation(summary = "Delete an account", 
        description = "Deletes a customer account based on the provided mobile number.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
        @ApiResponse(responseCode = "417", description = "Exception failed"),
        @ApiResponse(responseCode = "500", description = "Failed to delete account")
    })

     @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                String mobileNumber) {
        boolean isDeleted = iaccountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
