package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.name("Alessio Zubiani")
				.build();
	}

	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto customerDto) {
		
		log.debug("Updating a customer...");
	}

	@Override
	public void deleteCustomer(UUID customerId) {
		
		log.debug("Deleting a customer...");
	}

}
