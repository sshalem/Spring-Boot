package com.jpa.one2many.bi.eager.controller;

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
import com.jpa.one2many.bi.eager.service.UserServiceImpl;
import com.jpa.one2many.bi.eager.entity.RoleEntity;
import com.jpa.one2many.bi.eager.entity.UserEntity;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    // **************************************
    // ***** Post Methods ***
    // **************************************

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<Object>(userServiceImpl.createUser(userEntity), null, HttpStatus.CREATED);
    }

    // *********************
    // ***** Get Methods ***
    // *********************
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<Object>(userServiceImpl.getUserById(id), null, HttpStatus.FOUND);
    }

    @GetMapping("/getUserByPid/{pid}")
    public ResponseEntity<?> getUserByPid(@PathVariable("pid") long pid) {
        return new ResponseEntity<Object>(userServiceImpl.getUserByPid(pid), null, HttpStatus.FOUND);
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
        return new ResponseEntity<Object>(userServiceImpl.getUserByName(name), null, HttpStatus.FOUND);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<Object>(userServiceImpl.getUserByEmail(email), null, HttpStatus.FOUND);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAlltUser() {
        List<UserEntity> users = userServiceImpl.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    // **************************************
    // ***** Put Methods ***
    // **************************************

    @PutMapping("/addRole/{userPid}")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleEntity roleEntity, @PathVariable("userPid") long userPid) {
        UserEntity returnedValue = userServiceImpl.addRoleToUser(userPid, roleEntity);
        return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
    }

    @PutMapping("/addRoleUpdateUser/{userPid}")
    public ResponseEntity<?> addRoleUpdateUser(@RequestBody UserEntity userEntity, @PathVariable("userPid") long userPid) {
        UserEntity returnedValue = userServiceImpl.addRoleUpdateUser(userPid, userEntity);
        return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
    }

    // **************************************`
    // ***** Delete Methods ***
    // **************************************
    @DeleteMapping("/removeRole/{userPid}/{role}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable("role") String role, @PathVariable("userPid") long userPid) {
        UserEntity returnedValue = userServiceImpl.removeRoleFromUser(userPid, role);
        return new ResponseEntity<Object>(returnedValue, null, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeUser/{userPid}")
    public ResponseEntity<?> removeUser(@PathVariable("userPid") long userPid) {
        userServiceImpl.removeUserByPid(userPid);
        return new ResponseEntity<Object>(userServiceImpl.getAllUsers(), null, HttpStatus.ACCEPTED);
    }
}
