package com.test.auditService.service;

import java.util.List;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.IgnoreAudit;
import org.springframework.stereotype.Service;

import com.test.auditService.model.Audit4jDB;
import com.test.auditService.repository.Audit4jDBRepository;

import lombok.RequiredArgsConstructor;

@Audit
@Service
@RequiredArgsConstructor
public class TestService {

  private final Audit4jDBRepository audit4jDBRepository;

  public List<Audit4jDB> getAudit() {
    return audit4jDBRepository.findAll();
  }

  public String hello() {
    return "hello";
  }

  @Audit(action = "TestService.hello2")
  public String hello2(String firstName, @IgnoreAudit String lastName) {
    return "hello : " + firstName + " " + lastName;
  }

}
