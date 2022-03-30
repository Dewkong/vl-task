package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public abstract class AbstractDiscount {
    public static String NAME;

    public AbstractDiscount(String name){
        NAME = name;
    }

    protected Receipt applySpecificDiscount(Receipt receipt, double discount){
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(1.0 - discount));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    };
    public abstract Receipt apply(Receipt receipt);
    protected abstract boolean shouldApply(Receipt receipt);
}
