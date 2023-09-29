package com.test.auditService.config;

import javax.servlet.http.HttpServletRequest;

import org.audit4j.core.MetaData;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.test.auditService.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Audit4jMetaData implements MetaData {

    private final UserService userService;

    @Override
    public String getActor() {

        return userService.getCurrentUserId();

    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    @Override
    public String getOrigin() {

        return getRequest().getMethod() + "  " + getRequest().getRequestURI();
    }

}
