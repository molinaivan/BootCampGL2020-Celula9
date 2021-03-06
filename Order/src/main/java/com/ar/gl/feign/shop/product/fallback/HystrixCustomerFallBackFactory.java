package com.ar.gl.feign.shop.product.fallback;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ar.gl.feign.dto.CustomerDTO;
import com.ar.gl.feign.shop.product.FeignCustomer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class HystrixCustomerFallBackFactory implements FeignCustomer {
	
	CustomerDTO customerDTO = CustomerDTO.builder()
							  .id(0l)
							  .name("none")
							  .surname("none")
							  .dni("none")
							  .build();

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<List<CustomerDTO>> findAll() {
		return new ResponseEntity<>(Arrays.asList(customerDTO), HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<CustomerDTO> getById(Long id) {
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<CustomerDTO> save(@Valid CustomerDTO customerDTO) {
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<CustomerDTO> update(Long id, @Valid CustomerDTO customerDTO) {
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<CustomerDTO> partialUpdate(Long id, CustomerDTO customerDTO) {
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<CustomerDTO> restore(Long id) {
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<String> delete(Long id) {
		return new ResponseEntity<>("No se pudo eliminar", HttpStatus.OK);
	}

	@Override
	@HystrixCommand(ignoreExceptions = TypeMismatchException.class)
	public ResponseEntity<String> softDelete(Long id) {
		return new ResponseEntity<>("No se pudo eliminar", HttpStatus.OK);
	}

}
