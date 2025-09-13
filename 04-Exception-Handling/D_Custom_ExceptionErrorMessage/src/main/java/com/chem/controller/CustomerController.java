package com.chem.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.chem.exception.ExceptionErrorMessage;
import com.chem.exception.NameAlreadyExistException;
import com.chem.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{name}")
    public ResponseEntity<Object> getName(@PathVariable("name") String name, WebRequest request) {
        try {
            return new ResponseEntity<Object>(customerService.getName(name), new HttpHeaders(), HttpStatus.OK);
        } catch (Exception em) {
            ExceptionErrorMessage errorMessage = new ExceptionErrorMessage();
            errorMessage.setTimestamp(new Date());
            errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorMessage.setError(HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()).getReasonPhrase());
            errorMessage.setException(NameAlreadyExistException.class.getName());
            errorMessage.setMessage(em.getMessage());
            errorMessage.setUriDescription(request.getDescription(false));
            return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
