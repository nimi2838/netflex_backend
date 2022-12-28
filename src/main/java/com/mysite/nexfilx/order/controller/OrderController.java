package com.mysite.nexfilx.order.controller;

import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.User.dto.UserDto;
import com.mysite.nexfilx.User.service.UserService;
import com.mysite.nexfilx.order.dao.OrderRepository;
import com.mysite.nexfilx.order.domain.Payorder;
import com.mysite.nexfilx.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("order")
    public String setOrder(@RequestBody Payorder payorder) {
<<<<<<< HEAD
        System.out.println("orderDate : " + payorder.getOrderDate());
=======

        System.out.println("orderDate : " + payorder.getOrderDate());

>>>>>>> 03da3c737a3760947dad5d4eea6d20a607a22188
        orderService.setOrder(payorder);
        userService.setOrder(payorder);
        return "payorder";

    }

    @PostMapping("getorder")
    public List<Payorder> getOrder(@RequestBody Payorder payorder, Model model) {
        List<Payorder> findorder = orderService.orderEmail(payorder);
        model.addAttribute("order", findorder);
        return findorder;
    }



//    @PostMapping("getorder")
//    public Payorder getOrder(@RequestBody Payorder payorder) {
//        Payorder findorder = orderService.orderEmail(payorder);
//        return findorder;
//    }


}
