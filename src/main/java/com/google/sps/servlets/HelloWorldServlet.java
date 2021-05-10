package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */

@WebServlet("/facts")
public class HelloWorldServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Creates and Initializes a ArrayList
        ArrayList<String> facts = new ArrayList<String>(){
            {
                add("My favorite sport is soccer!");
                add("My favorite hero is Spiderman");
                add("I love music!!");
            }
        };
        // Convert the server facts to JSON
        ArrayList serverFacts = new ArrayList(facts);

        String json = new Gson().toJson(serverFacts);
        response.setContentType("application/json;");
        response.getWriter().println(json);

    }
}
