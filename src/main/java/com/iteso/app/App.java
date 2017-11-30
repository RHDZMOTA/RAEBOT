package com.iteso.app;

import com.iteso.app.util.Dictionary;
import com.iteso.app.util.WordCounter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("all")
public class App extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String stringWord = req.getParameter("word");
        Dictionary.add(stringWord);
        WordCounter.add(stringWord);

        String definition = Dictionary.getDefinition(stringWord);

        resp.setContentType("application/json");
        resp.getWriter().println(definition);
    }
}
