package com.test.auditService.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Random random; // inject keycloak to get the current userId

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  public String getCurrentUserId() {

    StringBuilder sb = new StringBuilder(20);
    for (int i = 0; i < 20; i++) {
      sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
    }
    return sb.toString();

  }

}
