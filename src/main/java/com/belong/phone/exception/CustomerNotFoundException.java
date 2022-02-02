package com.belong.phone.exception;

public class CustomerNotFoundException extends RuntimeException{
  
  public CustomerNotFoundException(String msg) { super(msg); }

  public CustomerNotFoundException(String msg, Throwable cause) { super(msg, cause); }
}
