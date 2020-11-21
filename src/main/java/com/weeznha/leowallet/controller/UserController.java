package com.weeznha.leowallet.controller;

import com.weeznha.leowallet.dto.UserDto;
import com.weeznha.leowallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.weeznha.leowallet.response.MessageResponse;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("api/users/")
    public ResponseEntity createUser(@RequestBody UserDto userDto){
        if(!userDto.getPassword().isEmpty() || !userDto.getUserName().isEmpty()){
            String encode = new BCryptPasswordEncoder().encode(userDto.getPassword());
            userDto.setPassword(encode);
        }
        if(userService.createUser(userDto)){
            return ResponseEntity.ok(new MessageResponse("nice"));
        }
        return ResponseEntity.badRequest().body("bad");
    }

    @GetMapping("api/users/")
    public ResponseEntity getUser(){
        System.out.println("get Test");
        return ResponseEntity.ok("nice");
    }
}
