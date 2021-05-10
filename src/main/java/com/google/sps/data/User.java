package com.google.sps.data;

public final class User {

  private final long id;
  private final String name;
  private final String email;
  private final String message;
  private final long timestamp;

// Creates a User object
  public User(long id, String name, String email, String message, long timestamp) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.message = message;
    this.timestamp = timestamp;
  }
}