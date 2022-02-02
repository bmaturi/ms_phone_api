package com.belong.phone.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.belong.phone.api.PhoneResponse;
import com.belong.phone.exception.CustomerNotFoundException;
import com.belong.phone.repository.PhoneRepository;
import com.belong.phone.repository.entity.PhoneDetail;

import org.springframework.stereotype.Service;

@Service
public class PhoneService {

  public PhoneResponse findAllPhoneNumbers() {
     List<String> phoneNumbers = PhoneRepository.phoneDetails.values()
      .stream().flatMap(Collection::stream).collect(Collectors.toList())
      .stream().map(PhoneDetail::getPhoneNumber).collect(Collectors.toList());
    return PhoneResponse.builder().phoneNumbers(phoneNumbers).build();
  }

  public PhoneResponse findAllPhoneNumbersForACustomer(String customerId) {
    List<PhoneDetail> phoneDetails = PhoneRepository.phoneDetails.get(customerId);
    
    if (phoneDetails == null) {
      throw new CustomerNotFoundException("Customer not found");
    }
    List<String> phoneNumbers = phoneDetails.stream().map(PhoneDetail::getPhoneNumber).collect(Collectors.toList());
    return PhoneResponse.builder().phoneNumbers(phoneNumbers).build();
  }
  
}
