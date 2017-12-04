package com.iteso.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.iteso.app.service.SmsResponseService;


@SuppressWarnings("all")
public class SmsResponseController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        // Extract parameters from request
        String userMessage = request.getParameter("Body");
        String userPhone = request.getParameter("From");
        // Instance of service and create string response.
        SmsResponseService smsResponseService = new SmsResponseService(userMessage, userPhone);
        String stringResp = smsResponseService.getStringResp();
        // Write out response.
        response.setContentType("application/xml");
        response.getWriter().print(stringResp);
    }
}
