package com.unoveo.controller;

import com.google.gson.JsonObject;
import com.unoveo.service.CalculationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        CalculationService cal = new CalculationService();
   double  result  = cal.doCalculation(sb.toString());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("response"+result);
        PrintWriter out = resp.getWriter();
out.print(result);
}}

