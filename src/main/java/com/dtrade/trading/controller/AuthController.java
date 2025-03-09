package com.dtrade.trading.controller;

import com.dtrade.trading.config.JwtProvider;
import com.dtrade.trading.model.UserEntity;
import com.dtrade.trading.repository.UserRepository;
import com.dtrade.trading.response.AuthResponse;
import com.dtrade.trading.service.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailService customeUserDetailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody UserEntity user) throws Exception {

        UserEntity isEmailExists = userRepository.findByEmail(user.getEmail());
        if (isEmailExists !=null)
        {
            throw new Exception("Email is already exist with another account");
        }

        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());

        Authentication auth = new UsernamePasswordAuthenticationToken (user.getEmail(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);

        UserEntity savedUser = userRepository.save(newUser);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse response = new AuthResponse();

        response.setJwt(jwt);
        response.setStatus(true);
        response.setMessage("register Success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody UserEntity user) throws Exception {

        String username = user.getEmail();
        String password= user.getPassword();


        Authentication auth = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setStatus(true);
        response.setMessage("login Success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customeUserDetailService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username ");
        }

        if (!password.equals(userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid password");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails, password , userDetails.getAuthorities());

    }
}
