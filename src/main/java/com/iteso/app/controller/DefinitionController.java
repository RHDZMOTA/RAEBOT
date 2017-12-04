package com.iteso.app.controller;


import com.iteso.app.service.DefinitionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("all")
public class DefinitionController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        // Extract parameters form request.
        String stringWord = req.getParameter("word");
        String stringSend = req.getParameter("send");
        // Create response.
        DefinitionService definitionService = new DefinitionService(stringWord, stringSend);
        String stringResp = definitionService.getStringResp();
        // Write out the response.
        resp.setContentType("application/json");
        resp.getWriter().println(stringResp);
    }
}
