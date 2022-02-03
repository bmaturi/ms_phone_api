package com.belong.phone.application;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.belong.phone.api.ActivateRequest;
import com.belong.phone.api.PhoneInfo;
import com.belong.phone.api.PhoneResponse;
import com.belong.phone.exception.EntityNotFoundException;
import com.belong.phone.repository.PhoneRepository;
import com.belong.phone.repository.entity.PhoneDetail;

import org.springframework.stereotype.Service;

@Service
public class PhoneService {

  public PhoneResponse findAllPhoneNumbers() {
     List<PhoneInfo> phoneNumbers = PhoneRepository.phoneDetails.values()
      .stream().flatMap(Collection::stream).collect(Collectors.toList())
      .stream().map(x -> new PhoneInfo(x.getPhoneNumber(), x.getIsActive())).collect(Collectors.toList());
    return PhoneResponse.builder().phoneNumbers(phoneNumbers).build();
  }

  public PhoneResponse findAllPhoneNumbersForACustomer(String customerId) {
    List<PhoneDetail> phoneDetails = PhoneRepository.phoneDetails.get(customerId);
    
    if (phoneDetails == null) {
      throw new EntityNotFoundException("Customer not found");
    }
    List<PhoneInfo> phoneNumbers = phoneDetails.stream().map(x -> new PhoneInfo(x.getPhoneNumber(), x.getIsActive())).collect(Collectors.toList());
    return PhoneResponse.builder().phoneNumbers(phoneNumbers).build();
  }

  public void activatePhoneNumber(ActivateRequest request) {
    List<PhoneDetail> phoneDetails = PhoneRepository.phoneDetails.get(request.getCustomerId());

    if (phoneDetails == null || phoneDetails.isEmpty()) {
      throw new EntityNotFoundException("Customer or PhoneNumber is not found.");
    }

    PhoneDetail filteredPhoneDetail = phoneDetails.stream()
                                    .filter(x -> x.getPhoneNumber().equals(request.getPhoneNumber()))
                                    .findAny()
                                    .orElse(null);
    
    if (filteredPhoneDetail == null) {
      throw new EntityNotFoundException("PhoneNumber not found for customer.");
    }
    filteredPhoneDetail.setIsActive(Boolean.TRUE);
    System.out.println(PhoneRepository.phoneDetails);
  }
  
}
