package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;
	

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {
		
		return new ResponseEntity<CustomerDto>(this.customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customerDto) {
		
		CustomerDto savedCustomer = this.customerService.saveCustomer(customerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/customer/".concat(savedCustomer.getId().toString()));
		
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<Object> updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto) {
		
		this.customerService.updateCustomer(customerId, customerDto);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
		
		this.customerService.deleteCustomer(customerId);
	}

}
