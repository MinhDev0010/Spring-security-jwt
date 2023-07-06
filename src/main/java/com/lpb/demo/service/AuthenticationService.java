package com.lpb.demo.service;

import com.lpb.demo.contract.auth.AuthenticationRequest;
import com.lpb.demo.contract.auth.RegisterRequest;

public interface AuthenticationService {
    int register(RegisterRequest request);

    String authenticate(AuthenticationRequest request);
}
