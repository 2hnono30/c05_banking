package com.codegym.service.customer;

import com.codegym.model.Customer;

import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.model.dto.DepositDTO;
import com.codegym.model.dto.WithdrawDTO;
import com.codegym.service.IGeneralService;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    Customer findByIdObj(Long id);

    Boolean existsByEmail(String email);

    Iterable<Customer> findAllByDeletedIsFalse();
    void doDeposit(Long customerId, DepositDTO depositDTO);

    void doWithdraw(Long customerId, WithdrawDTO withdrawDTO);

    Optional<DepositDTO> findByIdWithDepositDTO(Long id);

    Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id);
    void incrementBalance(BigDecimal balance, Long id);

    void reduceBalance(BigDecimal balance, Long id);

}
