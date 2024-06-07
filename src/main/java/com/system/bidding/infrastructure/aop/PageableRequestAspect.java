package com.system.bidding.infrastructure.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
@Aspect
@Component
public class PageableRequestAspect {

    @Pointcut(value = "@annotation(com.system.bidding.infrastructure.annotation.PageableRequest)")
    public void pageableRequest() {
    }

    @Before(value = "pageableRequest()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("doBefore: {}", Arrays.stream(args).count());
    }

}
