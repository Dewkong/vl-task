package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        //get products from basket
        List<Product> productsInBasket = basket.getProducts();
        //count quantity of each product
        HashMap<Product, Integer> productsQuantity = new HashMap<>();
        for (Product product : productsInBasket){
            if (productsQuantity.containsKey(product)){
                productsQuantity.put(product, productsQuantity.get(product) + 1);
            }else{
                productsQuantity.put(product, 1);
            }
        }
        //create entry for each product
        for (Product product : productsQuantity.keySet()){
            receiptEntries.add(new ReceiptEntry(product, productsQuantity.get(product)));
        }

        return new Receipt(receiptEntries);
    }
}
