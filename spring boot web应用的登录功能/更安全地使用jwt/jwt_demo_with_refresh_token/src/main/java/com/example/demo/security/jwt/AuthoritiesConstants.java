package com.example.demo.security.jwt;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    
    public static final String ACCOUNT_TEMP = "ACCOUNT_TEMP";//临时账号
    public static final String ACCOUNT_NORLMAL = "NORLMAL";//正常账号
    public static final String ACCOUNT_ANONYMOUS = "ANONYMOUS";//匿名账号
    

    private AuthoritiesConstants() {
    }
}
