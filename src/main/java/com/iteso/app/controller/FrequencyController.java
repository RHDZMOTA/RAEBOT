package com.iteso.app.controller;

import com.iteso.app.util.Jsonifier;
import com.iteso.app.util.WordCounter;

import javax.servlet.ServletConfig;
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

        resp.setContentType("application/json");
        resp.getWriter().println(Jsonifier.toJson(WordCounter.getInstance().getCore()));
    }
}
