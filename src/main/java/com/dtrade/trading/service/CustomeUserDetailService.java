package com.dtrade.trading.service;

import com.dtrade.trading.model.UserEntity;
import com.dtrade.trading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user==null)
        {
            throw  new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        return new User(
                user.getEmail(),
                user.getPassword(),
                grantedAuthorityList );
    }
}
