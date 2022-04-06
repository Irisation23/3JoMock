package com.nhnacademy.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    private CustomerRepository repository;
    private PaymentService service;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        service = new PaymentService(repository);
        service.setAccrualRatePolicy(new AccrualRatePolicy(0.01f, "Homeplus"));
    }

    @Test
    void pay() {
        String customerId = "jordan";
        long amount = 1000L;
        Customer customer = new Customer(customerId,10000L,"000");
        when(repository.findById(customerId)).thenReturn(customer);

        Receipt result = service.pay(amount,customer.getCustomerId());

        assertThat(customer.getMoney()).isEqualTo(9000L);
        assertThat(customer.getPoint()).isEqualTo(10L);
        assertThat(result).isNotNull();
        assertThat(result.getAmount()).isEqualTo(amount);
        assertThat(result.getCustomerId()).isEqualTo(customerId);

        verify(repository).findById(customerId);
    }

    @Test
    void pay_notEnoughMoney_throwInvalidAmountException() {
        String customerId = "jordan";
        long amount = 1000L;
        long money = 100;

        Customer customer = new Customer(customerId, money,"000");
        when(repository.findById(customerId)).thenReturn(customer);

        assertThatThrownBy(() -> service.pay(amount, customerId))
            .isInstanceOf(InvalidAmountException.class)
            .hasMessageContainingAll("negative");

        verify(repository).findById(customerId);
    }

    @Test
    void pay_invalidAmount_throwInvalidAmountException() {
        String customerId = "jordan";
        long amount = -10L;
        long money = 10000L;

        Customer customer = new Customer(customerId, money,"000");
        when(repository.findById(customerId)).thenReturn(customer);

        assertThatThrownBy(() -> service.pay(amount, customerId))
            .isInstanceOf(InvalidAmountException.class)
            .hasMessageContainingAll("negative");

        verify(repository, never()).findById(customerId);
    }

    @Test
    void pay_unknownId_throwUnknownIdException() {
        String customerId = "unknownId";
        long amount = 1_000L;

        assertThatThrownBy(() -> service.pay(amount, customerId))
            .isInstanceOf(UnknownIdException.class)
            .hasMessageContainingAll("Pay Failed", customerId);

        verify(repository).findById(customerId);
    }

    @Test
    void pay_failSendSms_successPay() {
        String customerId = "jordan";
        long amount = 1_000L;
        long money = 10_000L;

        Customer customer = new Customer(customerId, money,"");
        when(repository.findById(customerId)).thenReturn(customer);

        assertThat(service.sendSms(new SmsDummy(customer.getPhone()))).isFalse();
        assertThat(service.pay(amount,customer.getCustomerId())).isNotNull();

        verify(repository).findById(customerId);
    }

}