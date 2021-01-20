package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label result;
    private long num1 = 0;
    private String operator = "";
    private boolean start = true;
    private Calculation cal = new Calculation();

    @FXML
    public void processNumbers(ActionEvent event){ //processing number
        if(start){ //reset the label to blank
            result.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText(); //getting the number
        result.setText(result.getText()+value); //set the label to display the number
    }

    @FXML
    public void processOperators(ActionEvent event){ //process the operator
        String value = ((Button) event.getSource()).getText();
        if(!value.equals("=")){
            if(!operator.isEmpty()){
                return;
            } else {
                operator = value;
                num1 = Long.parseLong(result.getText());
                result.setText("");
            }
        } else {
            if(operator.isEmpty()){
                return;
            } else {
                long num2 = Long.parseLong(result.getText());
                double output = cal.calculate(num1, num2, operator);
                result.setText(String.valueOf(output));
                operator = ""; //reset the operator
                start = true; //reset the start to true to continue next function
            }
        }
    }
}
