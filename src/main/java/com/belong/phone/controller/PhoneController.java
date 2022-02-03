package com.belong.phone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.belong.phone.api.ActivateRequest;
import com.belong.phone.api.PhoneResponse;
import com.belong.phone.application.PhoneService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Validated
public class PhoneController {

  private final PhoneService service;

  @GetMapping("/v1/phonenumbers")
  public ResponseEntity<PhoneResponse> fetchAllPhoneNumbers() {
    return new ResponseEntity<>(service.findAllPhoneNumbers(), HttpStatus.OK);
  } 

  @GetMapping("/v1/{customerId}/phonenumbers")
  public ResponseEntity<PhoneResponse> fetchAllPhoneNumbersForACustomer(@PathVariable final String customerId) {
    return new ResponseEntity<>(service.findAllPhoneNumbersForACustomer(customerId), HttpStatus.OK);
  }

  @PutMapping("/v1/activate")
  public ResponseEntity<Object> activatePhoneNumber(@Valid @RequestBody ActivateRequest request) {
    service.activatePhoneNumber(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
}
