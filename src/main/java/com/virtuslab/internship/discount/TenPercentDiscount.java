package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount extends AbstractDiscount {
    public TenPercentDiscount() {
        super("TenPercentDiscount");
    }

    @Override
    public Receipt apply(Receipt receipt) {
        return super.applySpecificDiscount(receipt, 0.1);
    }

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) > 0;
    }
}
