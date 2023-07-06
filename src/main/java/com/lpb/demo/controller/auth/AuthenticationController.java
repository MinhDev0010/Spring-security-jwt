package com.lpb.demo.controller.auth;

import com.lpb.demo.contract.ResponsesContract;
import com.lpb.demo.contract.auth.AuthenticationRequest;
import com.lpb.demo.contract.auth.RegisterRequest;
import com.lpb.demo.contract.auth.Responses;
import com.lpb.demo.contract.auth.ResponsesError;
import com.lpb.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            int status = authenticationService.register(request);
            return ResponseEntity.ok(new ResponsesContract("200", "Register is successfully", status));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponsesError("ERROR", e.getMessage(), 500));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request) {
        String token = authenticationService.authenticate(request);
        try {
            if (token.startsWith("User")) {
                return ResponseEntity
                        .ok(new ResponsesError("Error", "Tên đăng nhập hoặc mật khẩu không đúng!!", 400));
            }
            return ResponseEntity
                    .ok(new Responses("200", "Login is successfully", token));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponsesError("ERROR", e.getMessage(), 500));
        }
    }
}
