package ru.itis.javalab.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.MethodRequestsService;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class MethodAspect {

    @Autowired
    MethodRequestsService methodRequestsService;

    @Before(value = "execution(* ru.itis.javalab.controllers..*(..))")
    public void changeCount(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String method = methodSignature.getMethod().getName();
        String className = methodSignature.getDeclaringType().getSimpleName();
        methodRequestsService.addCount(method, className);
    }
}
