package com.system.bidding.ports.incoming.adapter.view;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Controller(value = "LoginController")
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginViewHttpAdapter {

    @GetMapping
    public String login() {
        return "login";
    }
}
