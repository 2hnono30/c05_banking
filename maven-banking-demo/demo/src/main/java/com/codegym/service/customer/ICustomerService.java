package com.codegym.service.customer;

import com.codegym.model.Customer;

import com.codegym.service.IGeneralService;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    Customer findByIdObj(Long id);

    Boolean existsByEmail(String email);

    Iterable<Customer> findAllByDeletedIsFalse();


    void incrementBalance(BigDecimal balance, Long id);

    void reduceBalance(BigDecimal balance, Long id);

}
