package unit.com.belong.phone.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.belong.phone.api.ActivateRequest;
import com.belong.phone.api.PhoneResponse;
import com.belong.phone.application.PhoneService;
import com.belong.phone.controller.PhoneController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PhoneControllerTest {

  @InjectMocks
  private PhoneController controller;
  @Mock
  private PhoneService service;

  private final static String VALID_CUSTOMER_ID = "22";

  private final static String VALID_PHONE_NUMBER = "2234567876";

  @Test
  public void fetchAllPhoneNumbers_verifyServiceInvoked() {

    when(service.findAllPhoneNumbers()).thenReturn(PhoneResponse.builder().build());

    controller.fetchAllPhoneNumbers();

    verify(service, times(1)).findAllPhoneNumbers();
  }

  @Test
  public void fetchAllPhoneNumbersForACustomer_verifyServiceInvoked() {

    when(service.findAllPhoneNumbersForACustomer(anyString())).thenReturn(PhoneResponse.builder().build());

    controller.fetchAllPhoneNumbersForACustomer("22");
    
    verify(service, times(1)).findAllPhoneNumbersForACustomer(anyString());
  }

  @Test
  public void activatePhoneNumber_verifyServiceInvoked() {
    ActivateRequest request = new ActivateRequest();
    request.setCustomerId(VALID_CUSTOMER_ID);
    request.setPhoneNumber(VALID_PHONE_NUMBER);
    controller.activatePhoneNumber(request);
    
    verify(service, times(1)).activatePhoneNumber(any(ActivateRequest.class));
  }

  
}
