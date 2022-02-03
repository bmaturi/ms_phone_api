package com.belong.phone.api;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateRequest {

  @Size(min = 1, max = 2)
  private String customerId;

  @Size(min = 1, max = 10)
  private String phoneNumber;
  
}
