package com.test.auditService.service;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.IgnoreAudit;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Audit
@Service
@RequiredArgsConstructor
public class TestService {

  public String hello() {
    return "hello";
  }

  @Audit(action = "TestService.hello2")
  public String hello2(String firstName, @IgnoreAudit String lastName) {
    return "hello : " + firstName + " " + lastName;
  }

}
