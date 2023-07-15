package com.spring30.spring310;

public class CustomerNotFoundException extends RuntimeException{
    
    public CustomerNotFoundException(int id) {
        super("Customer with id " + id + " not found.");
      }
}
