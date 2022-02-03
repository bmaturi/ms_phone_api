package com.belong.phone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
  @Operation(
        summary = "Fetches all phone numbers for a customer.",
        description = "Fetches all phone numbers for a customer.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhoneResponse.class))
            ),
            @ApiResponse(description = "Customer Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )
  public ResponseEntity<PhoneResponse> fetchAllPhoneNumbersForACustomer(@PathVariable final String customerId) {
    return new ResponseEntity<>(service.findAllPhoneNumbersForACustomer(customerId), HttpStatus.OK);
  }

  @PutMapping("/v1/activate")
  @Operation(
        summary = "Activates the given phone number for the customer provided.",
        description = "Activates the given phone number for the customer provided.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200"),
            @ApiResponse(description = "Customer or phone number not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )
  public ResponseEntity<Object> activatePhoneNumber(@Valid @RequestBody ActivateRequest request) {
    service.activatePhoneNumber(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
}
