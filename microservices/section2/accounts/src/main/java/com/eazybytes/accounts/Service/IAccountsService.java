package com.eazybytes.accounts.Service;

import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.Entity.Customer;

public interface IAccountsService {
    
    /** 
     * Creates a new account for the given customer.
     *
     * @param customer The customer for whom the account is to be created.
     */

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}