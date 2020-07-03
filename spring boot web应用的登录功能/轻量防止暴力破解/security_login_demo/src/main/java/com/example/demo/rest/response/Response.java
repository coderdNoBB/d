package com.example.demo.rest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@ApiModel
@Data
@lombok.Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @ApiModelProperty(value = "状态码 0 正常, -1 不正常")
    private int code;

    @ApiModelProperty(value = "当result为 0 获取这个值")
    private Object data;

    @ApiModelProperty(value = "当result不为 0 获取这个值")
    private String message;

    @ApiModelProperty(value = "当result为 0 获取这个值")
    private String qrCode;
    
    private Long count;

    /**
     * @param object
     * @return
     */
    public static Response success(Object object) {
    	Response o = new Response();
        o.setData(object);
        o.setCode(0);
        return o;
    }
    /**
     * @param object
     * @return
     */
    public static Response successQRCode(Object object, String qrCode) {
    	Response o = new Response();
        o.setData(object);
        o.setCode(0);
        o.setQrCode(qrCode);
        return o;
    }
    
    /**
     * @param object
     * @return
     */
    public static Response success(Object object,String message) {
    	Response o = new Response();
        o.setData(object);
        o.setCode(0);
        o.setMessage(message);
        return o;
    }

    /**
     * @return
     */
    public static Response page(Object object,Long count) {
    	Response o = new Response();
        o.setData(object);
        o.setCount(count);
        o.setCode(0);
        return o;
    }
    
    /**
     * @return
     */
    public static Response success() {
        return success(null);
    }

    /**
     * @param message
     * @return
     */
    public static Response fail(int code, String message) {
        return Response
                .builder()
                .code(code)
                .message(message)
                .build();
    }

    /**
     * @return
     */
    public static Response fail() {
        return fail(-1, "系统错误");
    }

    /**
     * @param message
     * @return
     */
    public static Response fail(String message) {
        return fail(-1, message);
    }
    
    public static Response expiredJwtToken(String message) {
        return fail(1001, message);
    }
}
