package com.nhnacademy.payment;

public class PaymentService {
    AccrualRatePolicy accrualRatePolicy;
    private CustomerRepository repository;

    public PaymentService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void setAccrualRatePolicy(AccrualRatePolicy accrualRatePolicy) {
        this.accrualRatePolicy = accrualRatePolicy;
    }

    public Receipt pay(long amount, String customerId){
        if (amount < 0L) {
            throw new InvalidAmountException();
        }

        Customer found = repository.findById(customerId);
        if (found == null) {
            throw new UnknownIdException(customerId);
        }

        found.payMoney(amount);
        if (found.getMoney() < 0) {
            throw new InvalidAmountException();
        }
        found.earnPoint((long)(amount * accrualRatePolicy.getAccrualRate()));

        sendSms(new SmsDummy(found.getPhone()));

        return new Receipt(amount, customerId);
    }

    public boolean sendSms(SmsDummy smsDummy) {
        return smsDummy.isSuccess();
    }
}
