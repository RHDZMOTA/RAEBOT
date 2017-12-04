package com.iteso.app.controller;

import com.iteso.app.service.FrecuencyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("all")
public class FrequencyController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        // Instance of service and create string response.
        FrecuencyService frecuencyService = new FrecuencyService();
        String stringResp = frecuencyService.getStringResp();
        // Write out the response.
        resp.setContentType("application/json");
        resp.getWriter().println(stringResp);
    }
}
