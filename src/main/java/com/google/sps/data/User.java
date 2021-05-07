package com.google.sps.data;

/** An item on a todo list. */
public final class User {

  private final long id;
  private final String name;
  private final String email;
  private final String message;


  public User(long id, String name, String email, String message) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.message = message;
  }
}