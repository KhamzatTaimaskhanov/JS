package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.models.Users;
import ru.itmentor.spring.boot_security.demo.repositories.UsersRepository;
import ru.itmentor.spring.boot_security.demo.security.UsersDetails;

import java.util.Optional;

@Service
public class UsersDetailServices implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UsersDetails(user.get());
    }

}
