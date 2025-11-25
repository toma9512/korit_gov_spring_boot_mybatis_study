package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.EditUserReqDto;
import com.korit.mybatis_study.dto.RemoveUserReqDto;
import com.korit.mybatis_study.dto.SigninReqDto;
import com.korit.mybatis_study.dto.SignupReqDto;
import com.korit.mybatis_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(userService.signup(signupReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUserAll() {
        return ResponseEntity.ok(userService.getUserAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/update")
    public ResponseEntity<?> editUser(@RequestBody EditUserReqDto editUserReqDto) {
        return ResponseEntity.ok(userService.editUser(editUserReqDto));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> removeUser(@RequestBody RemoveUserReqDto removeUserReqDto) {
        return ResponseEntity.ok(userService.removeUser(removeUserReqDto));
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(userService.signin(signinReqDto));
    }
}
