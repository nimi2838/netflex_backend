package com.mysite.nexfilx.auth;

import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.User.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("받은 username : " + username);
        Optional<User> user = userRepository.findByUseremail(username);
        if(user.get().getUseremail() == null) {
            return null;
        }
        System.out.println("username : " + user.get().getUseremail());
        System.out.println("password : " + user.get().getPassword());
        return new PrincipalDetails(user.get());
    }
}
