package it.unisannio.eshop.eshop.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import it.unisannio.eshop.eshop.Database.User_Repository;
import it.unisannio.eshop.eshop.Entities.UserEntity;

import java.util.HashSet;

import java.util.Set;

@Service
public class User_Auth implements UserDetailsService {
    private final User_Repository userRepository;
    @Autowired
    public User_Auth(User_Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(email);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (String authority : user.getRole()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                System.out.println(user.getEmail());
                System.out.println(authority+" "+user.getRole());
            }

            return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }


}