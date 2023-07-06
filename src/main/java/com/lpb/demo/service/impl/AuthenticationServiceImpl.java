package com.lpb.demo.service.impl;

import com.lpb.demo.contract.auth.AuthenticationRequest;
import com.lpb.demo.contract.auth.RegisterRequest;
import com.lpb.demo.entity.Users;
import com.lpb.demo.enums.Role;
import com.lpb.demo.reposirory.UserRepository;
import com.lpb.demo.service.AuthenticationService;
import com.lpb.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager manager;

    @Override
    public int register(RegisterRequest request) {
        try {
            Users users = Users.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(Role.USER)
                    .email(request.getEmail())
                    .fullname(request.getFullname())
                    .gender(request.getGender())
                    .build();
            userRepository.save(users);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String authenticate(AuthenticationRequest request) {
        Users users = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (users == null || !passwordEncoder.matches(request.getPassword(), users.getPassword())) {
            return "User";
        } else {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));
            String jwt = jwtService.generateToken(users);
            return jwt;
        }
    }
}
