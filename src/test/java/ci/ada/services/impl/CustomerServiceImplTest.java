package ci.ada.services.impl;

import ci.ada.Repository.CustomerRepository;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    private static final Long id = 3L;
    private static final String slug = "Test Customer";

    private static CustomerDTO customerDTO;
    private static CustomerDTO customerDTOResponse;

    private static CustomerEntity customerEntity;
    private static CustomerEntity customerEntityResponse;

    private static final List<CustomerDTO> customerDTOList = new ArrayList<>();
    private static final List<CustomerEntity> customerEntitiesList = new ArrayList<>();

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerMapper = mock(CustomerMapper.class);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);

        customerDTO = new CustomerDTO();
        customerDTO.setSlug(slug);

        customerEntity = new CustomerEntity();
        customerEntity.setSlug(slug);

        customerEntityResponse = new CustomerEntity();
        customerEntityResponse.setId(id);
        customerEntityResponse.setSlug(slug);

        customerDTOResponse = new CustomerDTO();
        customerDTOResponse.setId(id);
        customerDTOResponse.setSlug(slug);

        customerEntitiesList.add(customerEntity);
        customerEntitiesList.add(customerEntityResponse);

        customerDTOList.add(customerDTO);
        customerDTOList.add(customerDTOResponse);
    }

    @Test
    void givenCustomerDTO_whenSave_thenReturnCustomerDTO() {
        when(customerMapper.toEntity(customerDTO)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntityResponse);
        when(customerMapper.toDTO(customerEntityResponse)).thenReturn(customerDTOResponse);

        CustomerDTO result = customerService.save(customerDTO);

        verify(customerMapper).toEntity(customerDTO);
        verify(customerRepository).save(customerEntity);
        verify(customerMapper).toDTO(customerEntityResponse);

        assertEquals(customerDTOResponse, result);
    }

    @Test
    void givenCustomerDTO_whenUpdate_thenReturnCustomerDTO() {
        when(customerMapper.toEntity(customerDTO)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntityResponse);
        when(customerMapper.toDTO(customerEntityResponse)).thenReturn(customerDTOResponse);

        CustomerDTO result = customerService.update(customerDTO);

        verify(customerMapper).toEntity(customerDTO);
        verify(customerRepository).save(customerEntity);
        verify(customerMapper).toDTO(customerEntityResponse);

        assertEquals(customerDTOResponse, result);
    }

    @Test
    void givenId_whenDelete_thenReturnVoid() {
        doNothing().when(customerRepository).deleteById(id);

        customerService.delete(id);

        verify(customerRepository).deleteById(id);
    }

    @Test
    void givenId_whenGetById_returnCustomerDTO() {
        when(customerRepository.findById(id)).thenReturn(Optional.of(customerEntityResponse));
        when(customerMapper.toDTO(customerEntityResponse)).thenReturn(customerDTOResponse);

        CustomerDTO result = customerService.getById(id);

        verify(customerRepository).findById(id);
        assertEquals(customerDTOResponse, result);
    }

    @Test
    void whenGetAll_return_CustomerDTOList() {
        when(customerRepository.findAll()).thenReturn(customerEntitiesList);

        for (int i = 0; i < customerEntitiesList.size(); i++) {
            when(customerMapper.toDTO(customerEntitiesList.get(i))).thenReturn(customerDTOList.get(i));
        }

        List<CustomerDTO> result = customerService.getAll();

        verify(customerRepository).findAll();

        assertEquals(customerDTOList.size(), result.size());
    }

    @Test
    void givenNonExistentId_whenGetById_thenThrowException() {
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            customerService.getById(id);
        });

        verify(customerRepository).findById(id);
    }

    @Test
    void givenCustomerDTOList_whenDeleteAll_thenReturnVoid() {
        doNothing().when(customerRepository).deleteAll();

        customerService.deleteAll(customerDTOList);

        verify(customerRepository).deleteAll();
    }

}
