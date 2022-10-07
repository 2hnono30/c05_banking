package com.codegym.model.dto;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositDTO implements Validator {

    private long customerId;
    private String fullName;
    private BigDecimal balance;

    @NotNull(message = "The transaction amount is required")
    @DecimalMin(value = "50", message = "Transaction Amount must be greater than or equal to 50")
    @DecimalMax(value = "10000000000", message = "Transaction Amount must be less than or equal to 10.000.000.000")
    private BigDecimal transactionAmount;

    public DepositDTO(long customerId, String fullName, BigDecimal balance) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.balance = balance;
    }


    public Deposit toDeposit(Optional<Customer> customer) {
        return new Deposit()
            .setCustomer(customer.get())
            .setTransactionAmount(transactionAmount);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
