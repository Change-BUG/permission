package com.ChangeBUG.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 账号封禁
 */
public class UserCountLockException extends AuthenticationException {

    public UserCountLockException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserCountLockException(String msg) {
        super(msg);
    }

}
