package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.util.List;

public class FifteenPercentDiscount extends AbstractDiscount{
    private final AbstractDiscount tenPercentDiscount;

    public FifteenPercentDiscount() {
        super("FifteenPercentDiscount");
        tenPercentDiscount = new TenPercentDiscount();
    }

    @Override
    public Receipt apply(Receipt receipt) {
        Receipt receiptAfterFirstDiscount = super.applySpecificDiscount(receipt, 0.15);
        if (receiptAfterFirstDiscount.totalPrice().equals(receipt.totalPrice())){
            return receipt;
        }else{
            return tenPercentDiscount.apply(receiptAfterFirstDiscount);
        }
    }

    @Override
    protected boolean shouldApply(Receipt receipt) {
        List<ReceiptEntry> entries = receipt.entries();
        int grainProducts = 0;
        for (ReceiptEntry entry : entries){
            if (entry.product().type() == Product.Type.GRAINS){
                grainProducts += entry.quantity();
            }
        }
        return grainProducts >= 3;
    }
}
