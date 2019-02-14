package com.codecool.shop.model;

public class Transaction {
    String paymentMethod;
    String username;
    String password;
    String cardname;
    String cardnumber;
    String expmonth;
    String expyear;
    String cvv;

    public PaymentMethod getPaymentMethod() {
        if (paymentMethod.equals("ccard")){
            return PaymentMethod.CREDIT_CARD;
        }
        else if (paymentMethod.equals("paypal")){
            return PaymentMethod.PAYPAL;
        }
        else{
            // no paymentMethod
            return null;
        }
    }
}
