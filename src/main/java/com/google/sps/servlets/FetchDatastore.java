package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servelet the lists all the users */
@WebServlet("/list-users")
public class FetchDatastore extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("User").setOrderBy(OrderBy.desc("timestamp")).build();

    QueryResults<Entity> results = datastore.run(query);

    List<User> users = new ArrayList<>();

    while (results.hasNext()) {
      Entity entity = results.next();

      long id = entity.getKey().getId();
      String name = entity.getString("name");
      String email = entity.getString("email");
      String message = entity.getString("message");
      long timestamp = entity.getLong("timestamp");

      User user = new User(id, name, email, message, timestamp);
      users.add(user);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(users));
  }
}