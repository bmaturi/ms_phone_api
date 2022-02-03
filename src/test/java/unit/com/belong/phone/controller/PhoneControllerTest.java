package unit.com.belong.phone.controller;

import com.belong.phone.application.PhoneService;
import com.belong.phone.controller.PhoneController;

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

  
  
}
