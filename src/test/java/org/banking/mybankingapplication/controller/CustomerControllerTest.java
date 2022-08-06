package org.banking.mybankingapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.banking.mybankingapplication.exception.CustomExceptionHandler;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.service.CustomerService;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private MockMvc mvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new CustomExceptionHandler()).build();

    }

    @Test
    void getAllCustomers() throws Exception {
        // init test values / given
        List<Customer> expectedCustomerList = getSampleTestCustomers();

        // stub - when
        when(customerService.getAllCustomers()).thenReturn(expectedCustomerList);

        MockHttpServletResponse response = mvc.perform(get("/v1/customer/all").accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Customer> actualCustomerList = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Customer>>() {
        });
        assertEquals(expectedCustomerList.size(), actualCustomerList.size());
    }

    @Test
    void getCustomerById() throws Exception {
        // init test values
        List<Customer> expectedCustomerList = getSampleTestCustomers();
        Customer expectedCustomer = expectedCustomerList.get(0);

        // stub - given
        when(customerService.getCustomerById(1L)).thenReturn(expectedCustomer);

        MockHttpServletResponse response = mvc.perform(get("/v1/customer/get/1").accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn().getResponse();

        //Manually set UTF-8 to avoid char unmatched error.
        response.setContentType("application/json;charset=UTF-8");

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Customer actualCustomer = new ObjectMapper().readValue(response.getContentAsString(), Customer.class);

//        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertAll(() -> assertEquals(expectedCustomer.getId(), actualCustomer.getId()), () -> assertEquals(expectedCustomer.getName(), actualCustomer.getName()), () -> assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname()), () -> assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail()), () -> assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress()));
    }

    @Test
    void getCustomerByName() throws Exception {
        // init test values
        List<Customer> expectedCustomerList = getSampleTestCustomers();
        Customer expectedCustomer = expectedCustomerList.get(0);

        // stub - given
        when(customerService.getCustomerByName(expectedCustomer.getName())).thenReturn(expectedCustomer);

        MockHttpServletResponse response = mvc.perform(get("/v1/customer/Fevzi").accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn().getResponse();

        //Manually set UTF-8 to avoid char unmatched error.
        response.setContentType("application/json;charset=UTF-8");

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Customer actualCustomer = new ObjectMapper().readValue(response.getContentAsString(), Customer.class);


        assertAll(() -> assertEquals(expectedCustomer.getId(), actualCustomer.getId()), () -> assertEquals(expectedCustomer.getName(), actualCustomer.getName()), () -> assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname()), () -> assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail()), () -> assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress()));

    }

    //Barely works, seek help :D
    @Test
    void createCustomer() throws Exception {
        // init test values

        Customer expectedCustomer = getSampleTestCustomers().get(0);
        ObjectMapper inputJson = new ObjectMapper();
        String inner = inputJson.writeValueAsString(expectedCustomer);

        // stub - given
        Mockito.when(customerService.createNewCustomerFromDTO(Mockito.any(CustomerDTO.class))).thenReturn(expectedCustomer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/customer/create").accept(MediaType.APPLICATION_JSON).content(inner).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        String outputInJson = response.getContentAsString();


        // then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat(outputInJson).isEqualTo(inner);
    }

    @Test
//Change body with string response
    void CreateCustomerFromEntity() throws Exception {
        // init test values

        Customer expectedCustomer = getSampleTestCustomers().get(0);
        ObjectMapper inputJson = new ObjectMapper();
        String inner = inputJson.writeValueAsString(expectedCustomer);

        // stub - given
        when(customerService.createNewCustomer(any(Customer.class))).thenReturn(expectedCustomer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/customer/create2").accept(MediaType.APPLICATION_JSON).content(inner).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        String outputInJson = response.getContentAsString();


        // then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat(outputInJson).isEqualTo(inner);

    }

    @Test
    void updateCustomerName() throws Exception {
        //init
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Evren");
        customerDTO.setSurname("Es");
        Customer updatedCustomer = new Customer(1L, "Evren", "Es", new Date(), "fevzi@hotmail.com", "+905312513462", "Istanbul", null);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedCustomerStr = objectWriter.writeValueAsString(expectedCustomer);

        //stub

        when(customerService.updateCustomerName("Fevzi", customerDTO)).thenReturn(updatedCustomer);
        //then

        MockHttpServletResponse response = mvc.perform(put("/v1/customer/Fevzi").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(expectedCustomerStr)).andDo(print()).andReturn().getResponse();

        response.setContentType("application/json;charset=UTF-8");

        //validate

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        verify(customerService, Mockito.times(1)).updateCustomerName(any(), any());

        Customer actualCustomer = new ObjectMapper().readValue(response.getContentAsString(), Customer.class);
        assertAll(() -> assertEquals(updatedCustomer.getId(), actualCustomer.getId()), () -> assertEquals(updatedCustomer.getName(), actualCustomer.getName()), () -> assertEquals(updatedCustomer.getSurname(), actualCustomer.getSurname()), () -> assertEquals(updatedCustomer.getEmail(), actualCustomer.getEmail()), () -> assertEquals(updatedCustomer.getAddress(), actualCustomer.getAddress()));

    }

    @Test
//Doesn't work
    void updateCustomerNameById() throws Exception {
        //init
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        Customer requestCustomer = new Customer(1L, "Orhan", "Çakman", new Date(), "fevzi@hotmail.com", "+905312513462", "Istanbul", null);
        Customer updatedCustomer = new Customer(1L, "Orhan", "Çakman", new Date(), "fevzi@hotmail.com", "+905312513462", "Istanbul", null);

        //stub

        when(customerService.updateCustomerByBody(requestCustomer)).thenReturn(updatedCustomer);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestCustomerStr = objectWriter.writeValueAsString(requestCustomer);


        //then
        MockHttpServletResponse response = mvc.perform(put("/v1/customer/update").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(requestCustomerStr)).andDo(print()).andReturn().getResponse();

        response.setContentType("application/json;charset=UTF-8");

//        Customer actualCustomer = new ObjectMapper().readValue(response.getContentAsString(), Customer.class);
//
//        System.out.println(actualCustomer);

        //validate
        assertEquals(response.getStatus(), HttpStatus.OK.value());


    }

    @Test
    void deleteCustomerById() throws Exception {
        // init test values
        willDoNothing().given(customerService).deleteCustomerById(1L);

        // stub - given
        ResultActions response = mvc.perform(delete("/v1/customer/delete/1"));

        // then
        response.andExpect(status().isOk()).andDo(print());

    }

    private List<Customer> getSampleTestCustomers() {
        List<Customer> sampleList = new ArrayList<>();
        Customer customer = new Customer(1L, "Fevzi", "Yüksel", new Date(), "fevzi@hotmail.com", "+905312513462", "Istanbul", null);
        Customer customer2 = new Customer(2L, "Ahmet", "Yılmaz", new Date(), "ahmet@hotmail.com", "+905312555555", "Ankara", null);
        Customer customer3 = new Customer(3L, "Mehmet", "Soylu", new Date(), "mehmet@hotmail.com", "+905312513333", "Izmir", null);
        sampleList.add(customer);
        sampleList.add(customer2);
        sampleList.add(customer3);
        return sampleList;
    }

    private Comparator<Customer> getCustomerComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0) return -1;
            if (o1.getId() - o2.getId() == 0) return 0;
            return 1;
        };
    }
}