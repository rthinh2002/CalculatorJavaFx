package com.company;
import java.math.BigDecimal;

public class Calculation {
    BigDecimal calculate(BigDecimal num1, BigDecimal num2, String operator){
        switch (operator){
            case "+":
                return num1.add(num2);
            case "-":
                return num1.subtract(num2);
            case "/":
                if(num2.compareTo(BigDecimal.ZERO) == 0){
                    return new BigDecimal(0.0);
                } else {
                    num1.divide(num2);
                }
            case "*":
                num1.multiply(num2);
            default:
                return new BigDecimal(0.0);
        }
    }
}
