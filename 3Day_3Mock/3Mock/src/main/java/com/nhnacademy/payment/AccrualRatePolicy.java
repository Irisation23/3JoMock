package com.nhnacademy.payment;

public class AccrualRatePolicy {
    private float accrualRate;
    private String accrualCode;

    public float getAccrualRate() {
        return accrualRate;
    }

    public String getAccrualCode() {
        return accrualCode;
    }

    public AccrualRatePolicy(float accrualRate, String accrualCode) {
        this.accrualRate = accrualRate;
        this.accrualCode = accrualCode;
    }

}
