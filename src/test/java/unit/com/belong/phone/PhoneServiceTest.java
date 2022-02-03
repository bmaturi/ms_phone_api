package unit.com.belong.phone;

import com.belong.phone.api.ActivateRequest;
import com.belong.phone.api.PhoneInfo;
import com.belong.phone.api.PhoneResponse;
import com.belong.phone.application.PhoneService;
import com.belong.phone.exception.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PhoneServiceTest {

  @InjectMocks
  private PhoneService phoneService;

  private final static String VALID_CUSTOMER_ID = "22";
  private final static String INVALID_CUSTOMER_ID = "88";

  private final static String VALID_PHONE_NUMBER = "2234567876";

  @Test
  public void findAllPhoneNumbers_success() {
    PhoneResponse response = phoneService.findAllPhoneNumbers();

    assertNotNull(response);
    assertNotNull(response.getPhoneNumbers());
    assertEquals(11, response.getPhoneNumbers().size());
  }

  @Test
  public void findAllPhoneNumbersForACustomer_whenValidCustomerIdIsProvided() {
    PhoneResponse response = phoneService.findAllPhoneNumbersForACustomer(VALID_CUSTOMER_ID);

    assertNotNull(response);
    assertNotNull(response.getPhoneNumbers());
    assertEquals(2, response.getPhoneNumbers().size());
  }

  @Test
  public void findAllPhoneNumbersForACustomer_throwsException_whenInvalidCustomerIdIsProvided() {
    assertThrows(EntityNotFoundException.class, () -> phoneService.findAllPhoneNumbersForACustomer(INVALID_CUSTOMER_ID));
  }

  @Test
  public void activatePhoneNumber_withValidCustomerIdPhoneNumber() {
    ActivateRequest request = new ActivateRequest();
    request.setCustomerId(VALID_CUSTOMER_ID);
    request.setPhoneNumber(VALID_PHONE_NUMBER);
    phoneService.activatePhoneNumber(request);

    PhoneResponse response = phoneService.findAllPhoneNumbersForACustomer(VALID_CUSTOMER_ID);

    PhoneInfo phoneInfo = response.getPhoneNumbers().stream()
                        .filter(x -> x.getPhoneNumber().equals(VALID_PHONE_NUMBER))
                        .findAny()
                        .orElse(null);
    
    assertNotNull(phoneInfo);
    assertEquals(Boolean.TRUE, phoneInfo.isActive());
  }

  @Test
  public void activatePhoneNumber_withInValidCustomerIdPhoneNumber() {
    ActivateRequest request = new ActivateRequest();
    request.setCustomerId(INVALID_CUSTOMER_ID);
    request.setPhoneNumber(VALID_PHONE_NUMBER);
    assertThrows(EntityNotFoundException.class, () -> phoneService.activatePhoneNumber(request));
  }
  
}
