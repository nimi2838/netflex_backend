package com.mysite.nexfilx.User.service;

import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.dto.UserDto;
import com.mysite.nexfilx.order.domain.Payorder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true) //토큰용, 일단사용X
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User join(User user) {

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   // yyyy-MM-dd HH:mm:ss
//        String format = formatter.format(LocalDate.now());
//        Date date = java.sql.Timestamp.valueOf(LocalDateTime.now());
//
//        user.setLastPaymentDate(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles("ROLE_USER");
        userRepository.save(user);

        return user;

    }


    public User setOrder(Payorder payorder) {
        Optional<User> opUser = userRepository.findByUseremail(payorder.getUseremail());
        if(opUser.isPresent()) {
            User orderUser = opUser.get();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   // yyyy-MM-dd HH:mm:ss

//            Date nowDate = formatter.format(payorder.getOrderDate());


            orderUser.setLastPaymentDate(payorder.getOrderDate());
            userRepository.save(orderUser);
            return orderUser;
        } else {
            return null;
        }
    }

    public Optional<User> getLastDate(UserDto userDto) {
        System.out.println("UserEmail : " + userDto.getUseremail());
        Optional<User> opDate = userRepository.findByUseremail(userDto.getUseremail());
        return opDate;
    }
    public User findId(String userId) {
        return userRepository.findByUseremail(userId).orElseThrow();
    }
//    public List<User> getProfile(String userId) {
//        return userRepository.findByUseremail(userId);
////                .stream()
////                .map(profile -> {
////                    User user = new ProfileName(profile);
////                    return user;
////                })
////                .toList();
//
////        return contentRepository.findAll();
//    }
//    public User setUserName(String useremail, String username, String gamename) {
//
//        Optional<User> opUser = userRepository.findByUseremail(useremail);
//        if(opUser.isPresent()) {
//            User loginedUser = opUser.get();
//
//            //            User loginedUser = opUser.get();
//            loginedUser.setUserName(username);
//            loginedUser.setGameName(gamename);
//
//
//            userRepository.save(loginedUser);
//        }
//        return null;
//
//    }
}
