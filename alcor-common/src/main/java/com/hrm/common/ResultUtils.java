package com.hrm.common;


/**
 * @author guchun
 * @description TODO
 * @date 2022/4/29 15:01
 */
public class ResultUtils {

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "success");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ResCodeEnum errorCode) {
        return new BaseResponse<>(errorCode.getCode());
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ResCodeEnum errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ResCodeEnum errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }

}
