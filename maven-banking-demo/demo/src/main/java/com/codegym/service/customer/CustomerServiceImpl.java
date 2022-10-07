package com.codegym.service.customer;

import com.codegym.model.Customer;

import com.codegym.model.Deposit;
import com.codegym.model.Withdraw;
import com.codegym.model.dto.DepositDTO;
import com.codegym.model.dto.WithdrawDTO;
import com.codegym.repository.CustomerRepository;
import com.codegym.repository.DepositRepository;
import com.codegym.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Iterable<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public void doDeposit(Long customerId, DepositDTO depositDTO) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        customerRepository.incrementBalance(depositDTO.getTransactionAmount(), customerId);

        depositRepository.save(depositDTO.toDeposit(customer));
    }

    @Override
    public void doWithdraw(Long customerId, WithdrawDTO withdrawDTO) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        customerRepository.reduceBalance(withdrawDTO.getTransactionAmount(), customerId);

        withdrawRepository.save(withdrawDTO.toWithdraw(customer));
    }


    @Override
    public Optional<DepositDTO> findByIdWithDepositDTO(Long id) {
        return customerRepository.findByIdWithDepositDTO(id);
    }

    @Override
    public Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id) {
        return customerRepository.findByIdWithWithdrawDTO(id);
    }


    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByIdObj(Long id) {
        return customerRepository.findByIdObj(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }


    @Override
    public void incrementBalance(BigDecimal balance, Long id) {
        customerRepository.incrementBalance(balance, id);
    }

    @Override
    public void reduceBalance(BigDecimal balance, Long id) {
        customerRepository.reduceBalance(balance, id);
    }
}
