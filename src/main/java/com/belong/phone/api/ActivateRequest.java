package com.belong.phone.api;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateRequest {

  // The customerId of the customer
  @Size(min = 1, max = 2)
  private String customerId;

  // phone number of the customer to be activated
  @Size(min = 1, max = 10)
  private String phoneNumber;
  
}
