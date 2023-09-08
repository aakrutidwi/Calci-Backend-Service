package com.unoveo;

import com.google.gson.Gson;

import com.google.gson.JsonArray;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;




@WebServlet("/calculator")
public class Calculator extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JsonArray arr = new JsonArray();
        Gson gson = new Gson();
        Button[] buttons = gson.fromJson(sb.toString(), Button[].class);

        double firstOperand = 0.0;
        double secondOperand = 0.0;
         String operator = null;


        for(int i =0;i< buttons.length;i++) {
            // case 1 : type number
            if (buttons[i].getType().equals("number")) {
                if(firstOperand == 0){
                    System.out.println("FirstOperand"+ firstOperand);
                    firstOperand = Double.parseDouble(buttons[i].getValue());
                }else {
                    System.out.println("SecondOperand"+secondOperand);
                    secondOperand = Double.parseDouble(buttons[i].getValue());
                    System.out.println("result="+ firstOperand);
                    firstOperand=  calculate(firstOperand,secondOperand,operator);
                }
            }

            // case 2 : type operation
            if (buttons[i].getType().equals("operation")) {
                System.out.println("operator"+operator);
            operator = buttons[i].getValue();
            }
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.write("Input Received");

    }

    public double calculate( double firstOperand, double secondOperand, String operator){
    double result=0;
        switch (operator){
            case "ADDITION":
                System.out.println("addition");
                result=  firstOperand + secondOperand;
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

