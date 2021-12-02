package com.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {

    public String getPasswordEncode(String password) {
        return DigestUtils.md5Hex(password);
    }

    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
