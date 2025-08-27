package ci.ada.services.impl;

import ci.ada.Repository.CustomerRepository;
import ci.ada.models.entity.CustomerEntity;
import ci.ada.services.CrudService;
import ci.ada.services.CustomerService;
import ci.ada.services.dto.CustomerDTO;
import ci.ada.services.dto.UserAccountDTO;
import ci.ada.services.mapper.CustomerMapper;
import ci.ada.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
        customerEntity = customerRepository.save(customerEntity);
        return customerMapper.toDTO(customerEntity);
    }

    @Override
    public CustomerDTO saveWithSlug(CustomerDTO customerDTO) {
        final String finalSlug = SlugifyUtils.generate("user");
        customerDTO.setSlug(finalSlug);
        return save(customerDTO);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        return save(customerDTO);
    }

    @Override
    public CustomerDTO partialUpdate(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(customerMapper::toDTO).toList();
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toDTO).orElseThrow(()->new IllegalArgumentException("Customer not found with id " + id));
    }

    @Override
    public CustomerDTO getBySlug(String slug) {
        return customerRepository.findBySlug(slug).map(customerMapper::toDTO).orElseThrow(()->new IllegalArgumentException("Customer not found with slug " + slug));
    }

    @Override
    public List<CustomerDTO> getAllById(Long id) {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);

    }

    @Override
    public void deleteAll(List<CustomerDTO> d) {
        customerRepository.deleteAll();
    }

    @Override
    public List<CustomerDTO> findAllByBankSlug(String bankSlug) {
        return customerRepository.findAllByBankEntitySlug(bankSlug).stream().map(customerMapper::toDTO).toList();
    }
}
