package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {
    @FXML
    private Label result;
    private BigDecimal num1;
    private String operator = "", percentage = "";
    private boolean start = true;
    private Calculation cal = new Calculation();

    @FXML
    public void processNumbers(ActionEvent event){ //processing number
        if(start){ //reset the label to blank
            result.setText("");
            start = false;
        }

        if(result.getText().contains("%")){ //stop the user from entering any new number after the %
            screenReset();
            return;
        }

        if(((Button) event.getSource()).getText().equals("+/-")){
            result.setText("-");
            return;
        }

        String value = ((Button) event.getSource()).getText(); //getting the number
        result.setText(result.getText()+value); //set the label to display the number
    }

    @FXML
    public void handlePercentageSign(ActionEvent event){
        if(result.getText().contains("%")){ //stop the user from entering any new number after the %
            screenReset();
            return;
        }

        percentage = result.getText();
        result.setText((result.getText())+((Button) event.getSource()).getText());

    }

    @FXML
    public void processOperators(ActionEvent event){ //process the operator
        String value = ((Button) event.getSource()).getText();
        BigDecimal percentageValue = new BigDecimal(0.01);
        if(!value.equals("=")){ //condition where it is not "=", processing the first number
            if(!operator.isEmpty()){ //check if there is previous operator exist
                return;
            } else {
                operator = value;
                if(result.getText().contains("%")){ //handling the percentage operator
                    num1 = new BigDecimal(percentage).multiply(percentageValue);
                    percentage = "";
                    result.setText("");
                } else {
                    num1 = new BigDecimal(result.getText());
                    result.setText("");
                }
            } //end else
        } else {
            if(operator.isEmpty()){
                return;
            } else {
                BigDecimal num2 = new BigDecimal(0.0); //general variable for BigDecimal data type

                if(result.getText().contains("%")){
                    num2 = new BigDecimal(percentage).multiply(percentageValue);
                    percentage = "";
                    BigDecimal output = (cal.calculate(num1, num2, operator)).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
                    result.setText(String.valueOf(output));
                } else {
                    num2 = new BigDecimal(result.getText());
                    BigDecimal output = (cal.calculate(num1, num2, operator)).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
                    System.out.println(output);
                    result.setText(String.valueOf(output));
                }

                operator = "";
                start = true;
            }
        } //end else
    }

    @FXML
    public void screenReset(){
        result.setText("0");
        start = true;
    }
}

