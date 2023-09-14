package com.unoveo.service;

import com.google.gson.Gson;
import com.unoveo.model.InputModel;

public class CalculationService {
    public double doCalculation (String input){


        Gson gson = new Gson();
        InputModel[] inputArray= gson.fromJson(input, InputModel[].class);
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>


        double firstOperand = 0.0;
        double secondOperand = 0.0;
        String operator = null;


// Iterate over the inputArray
        for(int i =0;i< inputArray.length;i++) {
            // case 1 :check if type is number
            if (inputArray[i].getType().equals("NUMBER")) {
                // check if firstOperand = 0 then set value in it
                if (firstOperand == 0.0) {
                    firstOperand = Double.parseDouble(inputArray[i].getValue());
                    System.out.println("FirstOperand: " + firstOperand);
                }
                else {
               // then it is second Operand , set the value in it
                    // call the calculate function
                    secondOperand =  Double.parseDouble(inputArray[i].getValue());
                    System.out.println("SecondOperand: " + secondOperand);
                    firstOperand = calculate(firstOperand, secondOperand, operator);
                    System.out.println("result=" + firstOperand);
                }
            }

            // case 2 : check if type is operation
            String op = "-";
            if (inputArray[i].getType().equals("OPERATION")) {
                // set the value in operator
                operator = inputArray[i].getValue();
                System.out.println("operator: " + operator);

                // if firstOperand is 0 and operator is substraction then in btn[i+1] concatenate the op string
                 if(firstOperand == 0.0 && operator.equals("SUBTRACTION")  ){
                     inputArray[i+1].setValue(op.concat(inputArray[i+1].getValue()));
                    System.out.println("after appending"+ operator);
                    System.out.println("If negative then perform this ");
                }
//
            }
        }
        System.out.println("final result"+ firstOperand);

    return  firstOperand;
    }

    public double calculate( double firstOperand, double secondOperand, String operator){
        double result=0;
        switch (operator){
            case "ADDITION":
                System.out.println("addition");
                result=  firstOperand + secondOperand;
                System.out.println(result);
                break;
            case "SUBTRACTION":
                System.out.println("subtraction");
                result= firstOperand - secondOperand;
                break;
            case "MULTIPLICATION":
                System.out.println("multiplication");
                result =firstOperand * secondOperand;
                break;
            case "DIVISION":
                System.out.println("division");
                result= firstOperand / secondOperand;
                break;
        }
        return result;
    }

}
