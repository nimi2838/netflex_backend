package com.mysite.nexfilx.kakao.controller;

import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.dto.UserDto;
import com.mysite.nexfilx.kakao.ReadyResponse;
import com.mysite.nexfilx.kakao.service.KakaoPay;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
//@RequestMapping("kakao")
public class SampleController {

    @Setter
    @Autowired
    private KakaoPay kakaopay;


    @GetMapping("test")
    public String test1() {
        return "testPage";
    }


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {
        log.info("kakaopay get............................................");

    }


    @PostMapping("kakaoPay")
    public String payReady(UserDto userDto) {
        System.out.println("체크체크체크체크체크 : " + userDto);

//        ReadyResponse readyResponse = kakaopay.payReady();

//        model.addAttribute("tid", readyResponse.getTid());
//        log.info("결재고유 번호: " + readyResponse.getTid());
        log.info("kakaoPay POST !!!!");


        return "redirect:" + kakaopay.payReady(userDto);
    }

//    @PostMapping("/kakaoPay")
//    public ReadyResponse kakaoPay() {
//        log.info("kakaoPay post............................................");
//
////        return kakaopay.payReady(1);
//            return kakaopay.payReady();
//    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

    }

    @GetMapping("/approval")
    public void approval() {
        log.info("approval get............................................");

    }


    @GetMapping("/kakaoPayCancel")
    public void kakaoPayCancel() {
        log.info("kakaoPayCancel get............................................");

    }

    @GetMapping("/kakaoPaySuccessFail")
    public void kakaoPaySuccessFail() {
        log.info("kakaoPaySuccessFail get............................................");

    }


}
