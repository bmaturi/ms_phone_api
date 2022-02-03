package component.com.belong.phone.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.belong.phone.PhoneApiApplication;

@SpringBootTest(classes = PhoneApiApplication.class)
@AutoConfigureMockMvc
public class PhoneControllerTest {
  
  @Autowired
  private MockMvc mvc;

  @Test
  public void fetchAllPhoneNumbers_success() throws Exception {
    mvc.perform(get("/v1/phonenumbers")).andExpect(status().isOk());
  }

  @Test
  public void fetchAllPhoneNumbersForACustomer_success() throws Exception {
    mvc.perform(get("/v1/22/phonenumbers")).andExpect(status().isOk());
  }

  @Test
  public void fetchAllPhoneNumbersForACustomer_invalidcustomerid() throws Exception {
    mvc.perform(get("/v1/99/phonenumbers")).andExpect(status().isNotFound());
  }

  @Test
  public void activatePhoneNumber_validcustomeridphonenumber() throws Exception {
    mvc.perform(put("/v1/activate")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content("{\"customerId\":\"33\",\"phoneNumber\":\"3331537876\"}"))
      .andExpect(status().isOk());
  }

  @Test
  public void activatePhoneNumber_invalidcustomeridphonenumber() throws Exception {
    mvc.perform(put("/v1/activate")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content("{\"customerId\":\"343\",\"phoneNumber\":\"3331537876\"}"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void activatePhoneNumber_nonexistentcustomeridphonenumber() throws Exception {
    mvc.perform(put("/v1/activate")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content("{\"customerId\":\"99\",\"phoneNumber\":\"3331537876\"}"))
      .andExpect(status().is4xxClientError());
  }
}
