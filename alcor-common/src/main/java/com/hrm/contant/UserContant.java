package com.hrm.contant;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 17:03
 */
public interface UserContant {

    /**
     * 用户登陆键
     */
    String USER_LOGIN_STATE = "userLoginState";

    Integer USER_ACCOUNT_MAXSIZE = 48;

    Integer USER_ACCOUNT_MIN_SIZE = 4;

    Integer USER_PASSWORD_MAXSIZE = 48;

    Integer USER_PASSWORD_MIN_SIZE = 4;

    String SALT = "user_password_salt_rand_ss12";

    String JWT_SECRET = "jwt_token_secret";

    String REDIS_TOKEN_SAVE_KEY = "REDIS_TOKEN_SAVE_KEY:";
}
