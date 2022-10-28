package com.jpa.one2one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.one2one.entity.UserEntity;
import com.jpa.one2one.entity.AddressEntity;
import com.jpa.one2one.exception.ResourceNotFoundException;
import com.jpa.one2one.repository.AddressRepository;
import com.jpa.one2one.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	
	// *******************************
	//       POST (create) methods
	//********************************
	
	@PostMapping("/address/{userId}")
	public ResponseEntity<AddressEntity> createAddress(@PathVariable(value = "userId") Long userId, @RequestBody AddressEntity address) {
		
		UserEntity user = userRepository
				.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + userId));
		
		// I must set the user before saving the Address
		address.setUser(user);
		
		AddressEntity details = addressRepository.save(address);

		return new ResponseEntity<>(details, HttpStatus.CREATED);
	}


	// *******************************
	//       GET methods
	//********************************
	
	@GetMapping({ "/address/{id}", "/address/{id}/address" })
	public ResponseEntity<AddressEntity> getDetailsById(@PathVariable(value = "id") Long id) {
		
		AddressEntity details = addressRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial Details with id = " + id));

		return new ResponseEntity<>(details, HttpStatus.OK);
	}


	// *******************************
	//       UPDATE methods
	//********************************
	
	@PutMapping("/address/{id}")
	public ResponseEntity<AddressEntity> updateAddress(@PathVariable("id") long id, @RequestBody AddressEntity address) {
		
		AddressEntity addressEntity = addressRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));

		addressEntity.setCity(address.getCity());
		addressEntity.setStreet(address.getStreet());
		

		return new ResponseEntity<>(addressRepository.save(addressEntity), HttpStatus.OK);
	}

	// *******************************
	//       DELETE methods
	//********************************
	
	@DeleteMapping("/address/{id}")
	public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") long id) {
		
		addressRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/{userId}/address")
	public ResponseEntity<AddressEntity> deleteAddressOfUser(@PathVariable(value = "userId") Long userId) {
		
		if (!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("Not found User with id = " + userId);
		}
		addressRepository.deleteByUserId(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
