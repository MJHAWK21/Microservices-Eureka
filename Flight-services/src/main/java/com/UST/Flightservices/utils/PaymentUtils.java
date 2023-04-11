package com.UST.Flightservices.utils;

import com.UST.Flightservices.exception.InsufficentAmountException;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {


    private static Map<String,Double>paymentmap=new HashMap<>();
    static {
        paymentmap.put("acc1",20000.00);
        paymentmap.put("acc2",50000.00);
        paymentmap.put("acc3",100000.00);
        paymentmap.put("acc4",34000.00);
        paymentmap.put("acc5",13000.00);
        paymentmap.put("acc6",900000.00);
        paymentmap.put("acc7",2300.00);
        paymentmap.put("acc8",589000.00);
    }
    public static boolean validateCreditLimit(String accountNo, double paidamount) throws InsufficentAmountException {
        if(paidamount>paymentmap.get(accountNo)){
             throw new InsufficentAmountException("insufficient amount...");
        }else{
            return true;
        }
    }
}
