package com.charity.spring_boot_post_upload_tools.controller;

import com.charity.spring_boot_post_upload_tools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> login(@RequestParam("employeeId") String employeeId,
                                        @RequestParam("employeeName") String employeeName) {
        boolean isValidUser = userService.validateUser(employeeId, employeeName);

        if (isValidUser) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
