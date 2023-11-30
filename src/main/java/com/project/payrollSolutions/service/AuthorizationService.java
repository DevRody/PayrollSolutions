package com.project.payrollSolutions.service;

import com.project.payrollSolutions.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    private final UserLoginRepository userLoginRepository;

    @Autowired
    AuthorizationService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String document) throws UsernameNotFoundException {
        return this.userLoginRepository.findByDocument(document);
    }
}
