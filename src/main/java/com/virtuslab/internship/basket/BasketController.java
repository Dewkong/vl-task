package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class BasketController {
    @GetMapping("/")
    public String index() {
        return "Basket endpoints";
    }

    @PostMapping(value="/basket", consumes="text/plain", produces="application/json")
    public Receipt createReceipt(@RequestBody String basketInfo){
        String[] basketList = basketInfo.split(",");
        Basket basket = new Basket();
        ProductDb productDb = new ProductDb();
        for (String basketEntry : basketList){
            try{
                Product product = productDb.getProduct(basketEntry);
                basket.addProduct(product);
            }catch (NoSuchElementException e){
                System.out.println("Error: " + e + "\nThere is no such product in our database");
            }
        }
        return new ReceiptGenerator().generate(basket);
    }
}
