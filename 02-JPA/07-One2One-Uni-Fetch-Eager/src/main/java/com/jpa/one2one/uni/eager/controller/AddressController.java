package com.jpa.one2one.uni.eager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.one2one.uni.eager.entity.AddressEntity;
import com.jpa.one2one.uni.eager.entity.UserEntity;
import com.jpa.one2one.uni.eager.exception.ResourceNotFoundException;
import com.jpa.one2one.uni.eager.repository.AddressRepository;
import com.jpa.one2one.uni.eager.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Transactional
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	
	// *******************************
	//       POST (create) methods
	//********************************
	
	@PostMapping("/address/{userId}")
	public ResponseEntity<AddressEntity> addAddressToUserByUserId(@PathVariable(value = "userId") Long userId, @RequestBody AddressEntity address) {
		
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
	
	@GetMapping( { "/address/{id}", "/address/{id}/address" } )
	public ResponseEntity<AddressEntity> getAddressById(@PathVariable(value = "id") Long id) {
		
		AddressEntity addressEntity = addressRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Address Details with id = " + id));

		return new ResponseEntity<>(addressEntity, HttpStatus.OK);
	}

	@GetMapping("/address/all")
	public ResponseEntity<List<AddressEntity>> getAllAddresses() {
		
		List<AddressEntity> addresses = addressRepository.findAll();				
		
		if (addresses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	// *******************************
	//       UPDATE methods
	//********************************
	
	@PutMapping("/address/{id}")
	public ResponseEntity<AddressEntity> updateAddressById(@PathVariable("id") long id, @RequestBody AddressEntity address) {
		
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
	public ResponseEntity<HttpStatus> deleteAddressById(@PathVariable("id") long id) {
		
		addressRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/address/{userId}/users")
	public ResponseEntity<AddressEntity> deleteAddressOfUser(@PathVariable(value = "userId") Long userId) {
		
		if (!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("Not found User with id = " + userId);
		}
		addressRepository.deleteByUserId(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/address/delete/all")
	public ResponseEntity<AddressEntity> deleteAllAddresses() {
				
		addressRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
