package com.mysite.nexfilx.User.controller;

import com.mysite.nexfilx.Contents.domain.NetflixContents;
import com.mysite.nexfilx.Contents.dto.NetflixDto;
import com.mysite.nexfilx.User.domain.ProfileImg;
import com.mysite.nexfilx.User.domain.ProfileName;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.dto.ProfileImgDto;

import com.mysite.nexfilx.User.dto.ProfileNameDto;
import com.mysite.nexfilx.User.dto.UserDto;
import com.mysite.nexfilx.User.dto.UserProfileDto;
import com.mysite.nexfilx.User.service.ProfileNameService;
import com.mysite.nexfilx.User.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ProfileNameService profileNameService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("")
    public String test() {
        return "test";
    }

    @PostMapping("/join")
    public String setUser(@RequestBody User user) {


        userService.join(user);

//        User user1 = userService.findId(user.getUseremail());
        ProfileImg profileImg = profileNameService.findById(String.valueOf(1));

        profileNameService.setNickName(user, user.getUseremail(), profileImg);

//        profileNameService.setProfileName(user, profileNameDto , profileImg);


        return "회원가입 완료";
    }

    @PostMapping("login")
    public User login(@RequestBody User user) {
        User loginedUser =  userService.login(user);

        return loginedUser;
    }

//    @PostMapping("/login")
//    public String login() {
//        return "로그인 완료";
//    }

//    @PostMapping("/login")
//    public UserDto login(@RequestBody User user) throws Exception {
//        UserDto loginedUser = userService.login(user);
//        return loginedUser;
//    }


    @PostMapping("/getLastPayDate")
    public Optional<User> getLastPaymentDate(@RequestBody UserDto userDto) {
        Optional<User> findDate =  userService.getLastDate(userDto);
        return findDate;
    }


    @PostMapping("/profile")
    public void setUserName(@RequestBody ProfileNameDto profileNameDto, @RequestParam("useremail") String userId){

        User user = userService.findId(userId);
        ProfileImg profileImg = profileNameService.findById(String.valueOf(1));

        System.out.println("profileName" + profileNameDto.getNickname());

        profileNameService.setProfileName(user, profileNameDto, profileImg);

    }



    @GetMapping("/getProfile")
    public UserProfileDto getNetflixContents(@RequestParam("useremail") String userId) {

        User user = userService.findId(userId);

        return profileNameService.getProfile(user);
    }

    @PatchMapping("updateprofile")
    public void updateProfile(@RequestParam("useremail") String userId, @RequestBody ProfileNameDto profileNameDto) {
        User user = userService.findId(userId);

        profileNameService.updateProfile(user, profileNameDto);
    }

    @PostMapping("deleteprofile")
    public void deleteProfile(@RequestBody ProfileName profileName) {
        profileNameService.deleteProfile(profileName);
    }


    @GetMapping("getprofileImg")
    public List<ProfileImg> getProfileImg() {
        return profileNameService.getImgAll();
    }

    @PostMapping("userSetProfileImg")
    public void userSerProfileImg(@RequestParam("useremail") String userId, @RequestBody ProfileNameDto profileNameDto) {
        User user = userService.findId(userId);
        ProfileImg profileImg = profileNameService.findById(profileNameDto.getImg());

//        System.out.println("Profile"+ profileNameDto);

        profileNameService.setProfileImg(user, profileImg, profileNameDto);
    }

//    @PostMapping("findProfileImg")
//    public void findProfileImg(@RequestBody ProfileImgDto profileImgDto) {
//        profileNameService.findByImg(profileImgDto.getId());
//    }





}
