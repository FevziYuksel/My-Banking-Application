package org.banking.mybankingapplication.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.banking.mybankingapplication.exception.DuplicateEntityException;
import org.banking.mybankingapplication.exception.EntityNotFoundException;
import org.banking.mybankingapplication.model.dto.CustomerDTO;
import org.banking.mybankingapplication.model.entity.Customer;
import org.banking.mybankingapplication.model.mapper.mapstruct.CustomerMapper;
import org.banking.mybankingapplication.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.StubInfo;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerService customerService;

//        @BeforeAll ????
//    public void setup() {
//        // init
//      // testCourse = new Course(2L, "Math");
//
//    }
//
//    @BeforeEach
//    public void setup() {
//
//
//
//        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");
//
//        // stub - when
//        Mockito.when(passengerRepository.findById(1)).thenReturn(Optional.of(expectedPassenger));
//    }
    private List<Customer> getSampleTestCustomers() {
    List<Customer> sampleList = new ArrayList<>();
    Customer customer = new Customer(1L,"Fevzi","Yüksel", LocalDate.now(),"fevzi@hotmail.com","+905312513462","Istanbul",null);
    Customer customer2 = new Customer(2L,"Ahmet","Yılmaz", LocalDate.now(),"ahmet@hotmail.com","+905312555555","Ankara",null);
    Customer customer3 = new Customer(3L,"Mehmet","Soylu", LocalDate.now(),"mehmet@hotmail.com","+905312513333","Izmir",null);
    sampleList.add(customer);
    sampleList.add(customer2);
    sampleList.add(customer3);
    return sampleList;
}

    private Comparator<Customer> getCustomerComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }

    @Ignore
    @Test
    void getAllCustomers() {
        // init step
        List<Customer> expCustomerList = getSampleTestCustomers();

        // stub - when step
        when(customerRepository.findAll()).thenReturn(expCustomerList); //

        // then - validate step
        List<Customer> actualCustomerList = customerService.getAllCustomers();

        assertEquals(expCustomerList.size(), actualCustomerList.size());
//        Assert.assertEquals(expCustomerList.size(), actualCustomerList.size());
        verify(customerRepository,times(1)).findAll();
        System.out.println("First: " + expCustomerList);
        expCustomerList = expCustomerList.stream().sorted(getCustomerComparator()).collect(Collectors.toList());
        actualCustomerList = actualCustomerList.stream().sorted(getCustomerComparator()).collect(Collectors.toList());

        //Convert loop to stream ?? Problem ??
        for (int i = 0; i < expCustomerList.size(); i++) {
            Customer currExpectedCourse = expCustomerList.get(i);
            Customer currActualCourse = actualCustomerList.get(i);
            assertEquals(currExpectedCourse.getId(), currActualCourse.getId());
            assertEquals(currExpectedCourse.getName(), currActualCourse.getName());
            assertEquals(currExpectedCourse.getSurname(), currActualCourse.getSurname());
            assertEquals(currExpectedCourse.getEmail(), currActualCourse.getEmail());
            assertEquals(currExpectedCourse.getAddress(), currActualCourse.getAddress());
            //..
        }

        System.out.println("Second : " + expCustomerList);

    }
    @Ignore
    @Test
    void getAllCustomers_AssertAll() {
        List<Customer> expCustomerList = getSampleTestCustomers();

        // stub - when step
        when(customerRepository.findAll()).thenReturn(expCustomerList); //

        // then - validate step
        List<Customer> actualCustomerList = customerService.getAllCustomers();

        assertEquals(expCustomerList.size(), actualCustomerList.size());
//        Assert.assertEquals(expCustomerList.size(), actualCustomerList.size());
        verify(customerRepository,times(1)).findAll();  //Why do we verify ??
        System.out.println("First: " + expCustomerList);
        expCustomerList = expCustomerList.stream().sorted(getCustomerComparator()).collect(Collectors.toList());
        actualCustomerList = actualCustomerList.stream().sorted(getCustomerComparator()).collect(Collectors.toList());


        for (int i = 0; i < expCustomerList.size(); i++) {
            Customer expCustomer= expCustomerList.get(i);
            Customer actualCustomer = actualCustomerList.get(i);
            assertAll(
                    () -> assertEquals(expCustomer.getId(), actualCustomer.getId()),
            () ->assertEquals(expCustomer.getName(), actualCustomer.getName()),
            () ->assertEquals(expCustomer.getSurname(), actualCustomer.getSurname()),
            () ->assertEquals(expCustomer.getEmail(), actualCustomer.getEmail()),
            () ->assertEquals(expCustomer.getAddress(), actualCustomer.getAddress())
            );
        }




        System.out.println("Second : " + expCustomerList);

    }


    @Test
    void getCustomerById_successful() {
        // init step
        Customer expCustomer = getSampleTestCustomers().get(1);
        Optional<Customer> optExpectedCustomer = Optional.of(expCustomer);

        // stub - when step
//        when(customerRepository.findById(any())).thenReturn(optExpectedCustomer);
        when(customerRepository.findById(anyLong())).thenReturn(optExpectedCustomer);


        // then - validate step
        Customer actualCustomer = customerService.getCustomerById(1L);

//        Assert.assertEquals(actualCustomer.getId(), expCustomer.getId());
//        Assert.assertEquals(actualCustomer.getName(), expCustomer.getName());
//Alternative better way:
        assertAll(
                () -> assertEquals(expCustomer.getId(), actualCustomer.getId()),
                () ->assertEquals(expCustomer.getName(), actualCustomer.getName()),
                () ->assertEquals(expCustomer.getSurname(), actualCustomer.getSurname()),
                () ->assertEquals(expCustomer.getEmail(), actualCustomer.getEmail()),
                () ->assertEquals(expCustomer.getAddress(), actualCustomer.getAddress())
        );
        // ...
    }
    @Test
    void getCustomerById_NOT_FOUND() {
        // stub - when step
//        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // then - validate step
        assertThrows(EntityNotFoundException.class,
                () -> {
                    Customer actualCustomer = customerService.getCustomerById(1L);
                }
        );

    }
    @Test
    void getCustomerByName_successful() {
        // init step
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        Optional<Customer> optionalExpectedCustomer = Optional.of(expectedCustomer);


        // stub - when step

        when(customerRepository.findByNameContainingIgnoreCase(any())).thenReturn(optionalExpectedCustomer);
        // then - validate step

        Customer actualCustomer = customerService.getCustomerByName(any());


        assertAll(
                () -> assertEquals(expectedCustomer.getId(), actualCustomer.getId()),
                () ->assertEquals(expectedCustomer.getName(), actualCustomer.getName()),
                () ->assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname()),
                () ->assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail()),
                () ->assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress())
        );


    }


    @Test
    void createNewCustomer_successful(){
        //init step
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        expectedCustomer.setId(null);


        // stub - when step
        when(customerRepository.save(any())).thenReturn(expectedCustomer);

        //then - validate step

        Customer actualCustomer = customerService.createNewCustomer(expectedCustomer);

        verify(customerRepository,times((1))).save(expectedCustomer);

        assertAll(
                () -> assertEquals(expectedCustomer.getId(), actualCustomer.getId()),
                () ->assertEquals(expectedCustomer.getName(), actualCustomer.getName()),
                () ->assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname()),
                () ->assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail()),
                () ->assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress())
        );

    }
    @Test //Mapper null error -> Mapper has to be mocked
    void createNewCustomerDto_successful(){
        //init step
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(expectedCustomer.getName());
        customerDTO.setSurname(expectedCustomer.getSurname());
        customerDTO.setAddress(expectedCustomer.getAddress());
        customerDTO.setEmail(expectedCustomer.getEmail());
        customerDTO.setPhoneNo(expectedCustomer.getPhoneNo());


        // stub - when step
        when(customerMapper.toEntity(customerDTO)).thenReturn(expectedCustomer);
        when(customerRepository.save(any())).thenReturn(expectedCustomer);

        //then - validate step


        Customer actualCustomer = customerService.createNewCustomerFromDTO(customerDTO); //Conflict with mapstruct

        verify(customerRepository,times((1))).save(expectedCustomer);

        assertAll(
                () -> assertEquals(expectedCustomer.getId(), actualCustomer.getId()),
                () ->assertEquals(expectedCustomer.getName(), actualCustomer.getName()),
                () ->assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname()),
                () ->assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail()),
                () ->assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress())
        );

    }

    @Test //How to test dublicates ?? I'm not how it passed :D
    void createNewCustomer_duplicate(){
        //init step
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        Customer actualCustomer = getSampleTestCustomers().get(0);
        Optional<Customer> optActualCustomer = Optional.of(actualCustomer);

        // stub - when step
        when(customerRepository
                .findCustomerByNameAndEmail(expectedCustomer.getName(),expectedCustomer.getEmail()))
                .thenReturn(optActualCustomer);

        //then - validate step

        assertThrows(DuplicateEntityException.class,
                () -> {
                    customerService.createNewCustomer(expectedCustomer);
                }
        );



    }

    @Test
    void updateCustomerName() {
        // init step
        Customer expCustomer = getSampleTestCustomers().get(0);
        Optional<Customer> optExpCustomer = Optional.of(expCustomer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Evren");
        customerDTO.setSurname("Es");


        // stub - when step
//        given(customerRepository.findByNameContainingIgnoreCase(expCustomer.getName())).willReturn(Optional.of(expCustomer)); //Alternative of when then
        when(customerRepository.findByNameContainingIgnoreCase(expCustomer.getName())).thenReturn(optExpCustomer);
        when(customerRepository.save(any())).thenReturn(expCustomer);
        Customer actualCustomer = customerService.updateCustomerName(expCustomer.getName(), customerDTO);


        // then - validate step
        assertAll(
                () -> assertEquals(expCustomer.getId(), actualCustomer.getId()),
                () ->assertEquals(expCustomer.getName(), actualCustomer.getName()),
                () ->assertEquals(expCustomer.getSurname(), actualCustomer.getSurname()),
                () ->assertEquals(expCustomer.getEmail(), actualCustomer.getEmail()),
                () ->assertEquals(expCustomer.getAddress(), actualCustomer.getAddress())
        );
    }

    @Test
    void deleteCustomerById(){
        // init step
        Long customerId = 1L;
        Customer expCustomer = getSampleTestCustomers().get(0);

        // stub - when step
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(expCustomer));
        doNothing().when(customerRepository).deleteById(customerId);

        // then - validate step
        customerService.deleteCustomerById(1L);

        verify(customerRepository, times(1)).deleteById(customerId);

    }

    /**Failed tests Incompatible localTime to Json conversion of Google Json object package
    @Test
    void getCustomerByName_successful_Json() {

        // init step
        Customer expectedCustomer = getSampleTestCustomers().get(0);
        Optional<Customer> optionalExpectedCustomer = Optional.of(expectedCustomer);
        String expectedCustomerJSON = ObjectExtensions.toJson(expectedCustomer);

        // stub - when step

        when(customerRepository.findByNameContainingIgnoreCase(any())).thenReturn(optionalExpectedCustomer);
        // then - validate step
        Customer actualCustomer = customerService.getCustomerByName(any());
        String actualCustomerJSON = ObjectExtensions.toJson(actualCustomer);

        assertEquals(expectedCustomerJSON, actualCustomerJSON);

    }
    @Test
    void getAllCustomersJson() {
        // init step (JavaFaker ??)
        List<Customer> expCustomerList = getSampleTestCustomers();
        String expectedListJSON = ObjectExtensions.toJson(expCustomerList);

        //stub - when step
        when(customerRepository.findAll()).thenReturn(expCustomerList);

        // then
        List<Customer> actualBookList = customerService.getAllCustomers();
        String actualListJSON = ObjectExtensions.toJson(actualBookList);

        // validate step
        assertEquals(actualListJSON, expectedListJSON);
        verify(customerRepository,times(1)).findAll();

    }
    */


}
class ObjectExtensions {

    public static  <O> String toJson(O object ) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String JsonObject = gson.toJson(object);
        return JsonObject;
    }
}

