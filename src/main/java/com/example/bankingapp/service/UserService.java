package com.example.bankingapp.service;

import com.example.bankingapp.entity.User;
import com.example.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow();
    }
    public void save(User user){
        userRepository.save(user);
    }
    public boolean existsByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent())
            return true;
        return false;
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
