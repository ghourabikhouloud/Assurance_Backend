package com.example.assuranceexam.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class AspectBonCourage {

    @After("execution(* com.example.assuranceexam.services.ImplService.get*(..))")
    public void logBonjour() {
        log.info("Bon courage!");
    }

}
