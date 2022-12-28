package com.mysite.nexfilx.User.dao;

import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUseremail(String useremail);

//    Optional<User> findByLastPaymentDate(String useremail);
}
