package com.belong.phone.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belong.phone.repository.entity.PhoneDetail;

public class PhoneRepository {

  public static Map<String, List<PhoneDetail>> phoneDetails = new HashMap<>(); 
  static {

    phoneDetails.put("11", List.of(new PhoneDetail("1134567876", Boolean.TRUE),
                                   new PhoneDetail("1134537876", Boolean.FALSE),
                                   new PhoneDetail("1131537876", Boolean.TRUE)));
    
    phoneDetails.put("22", List.of(new PhoneDetail("2234567876", Boolean.FALSE),
                                   new PhoneDetail("2231537876", Boolean.FALSE)));
    
    phoneDetails.put("33", List.of(new PhoneDetail("3334567876", Boolean.FALSE),
                                   new PhoneDetail("3334537876", Boolean.FALSE),
                                   new PhoneDetail("3331537876", Boolean.FALSE)));
    
    phoneDetails.put("44", List.of(new PhoneDetail("4434567876", Boolean.TRUE),
                                   new PhoneDetail("4434537876", Boolean.FALSE),
                                   new PhoneDetail("4431537876", Boolean.FALSE)));
    
    phoneDetails.put("55", List.of());
  }
  
}
