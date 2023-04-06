package com.UST.Userservice.controller;


import com.UST.Userservice.VO.ResponseTemplateVO;
import com.UST.Userservice.entity.User;
import com.UST.Userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
 @Autowired
    private UserService userservice;

 @PostMapping("/")
    public User saveUser(@RequestBody User user){
     log.info("inside saveUser of Usercontroller");
     return userservice.saveUser(user);
 }
 @GetMapping("/{id}")
    public ResponseTemplateVO getUserWITHDepartment(@PathVariable("id") Long userId){
    log.info("Inside getuserwithdepartment of UserController");
     return userservice.getUserwithdepartment(userId);
 }
}
