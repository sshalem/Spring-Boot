package com.jpa.one2one.bi.lazy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.one2one.bi.lazy.dao.AddressDaoImpl;
import com.jpa.one2one.bi.lazy.entity.AddressEntity;
import com.jpa.one2one.bi.lazy.entity.UserEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressDaoImpl addressDaoImpl;

	
	// *******************************
	//       POST (create) methods
	//********************************
	
	@PostMapping("/address/{userId}")
	public ResponseEntity<UserEntity> addAddressToUserByUserId(@PathVariable(value = "userId") long userId, @RequestBody AddressEntity address) {		
		return new ResponseEntity<>(addressDaoImpl.addAddressToUser(address, userId), HttpStatus.CREATED);
	}


	// *******************************
	//       GET methods
	//********************************
	
	@GetMapping( { "/address/{id}", "/address/{id}/address" } )
	public ResponseEntity<AddressEntity> getAddressById(@PathVariable(value = "id") long id) {
		return new ResponseEntity<>(addressDaoImpl.getAddressById(id), HttpStatus.OK);
	}

	@GetMapping("/address/all")
	public ResponseEntity<List<AddressEntity>> getAllAddresses() {
		
		List<AddressEntity> addresses = addressDaoImpl.getAllAddresses();		
		
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
		return new ResponseEntity<>(addressDaoImpl.updateAddress(id, address), HttpStatus.OK);
	}

	// *******************************
	//       DELETE methods
	//********************************
	
	@DeleteMapping("/address/{id}")
	public ResponseEntity<HttpStatus> deleteAddressById(@PathVariable("id") long id) {		
		addressDaoImpl.deleteAddress(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/address/{userId}/users")
	public ResponseEntity<AddressEntity> deleteAddressOfUser(@PathVariable(value = "userId") long userId) {
		
//		if (!userRepository.existsById(userId)) {
//			throw new ResourceNotFoundException("Not found User with id = " + userId);
//		}
		addressDaoImpl.deleteAddressOfUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/address/delete/all")
	public ResponseEntity<AddressEntity> deleteAllAddresses() {
				
		addressDaoImpl.deleteAllAddresses();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
