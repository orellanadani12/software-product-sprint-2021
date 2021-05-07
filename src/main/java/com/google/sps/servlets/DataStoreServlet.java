package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/message")
public class DataStoreServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("text/html;");
    response.getWriter().println("<h1>Shoutbox</h1>");
    response.getWriter().println("<ul>");
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    Query<Entity> query = Query.newEntityQueryBuilder()
      .setKind("Question")
      .setOrderBy(OrderBy.desc("timestamp"))
      .build();
    QueryResults<Entity> results = datastore.run(query);

    while (results.hasNext()) {
      Entity entity = results.next();
      String question = entity.getString("question");
      String answer = entity.getString("answer");
      response.getWriter().println("<li>" + question + "</li>");
      response.getWriter().println("<li>" + answer + "</li>");
    }
    
    response.getWriter().println("</ul>");
    response.getWriter().println("<p><a href=\"/\">Back</a></p>");
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String question = request.getParameter("question");
    String answer = request.getParameter("answer");
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Question");
    FullEntity questionEntity = Entity.newBuilder(keyFactory.newKey())
      .set("question", question)
      .set("answer", answer)
      .build();
    datastore.put(questionEntity);

    // Redirect to /message.
    // The request will be routed to the doGet() function above.
    response.sendRedirect("/message");
  }
}