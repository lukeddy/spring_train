package com.tzq.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-7-17
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public class AjaxResponse {
    public static final String SUCCESS="success";
    public static final String FAIL="fail";

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    private String adminId;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public String getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(String ajaxResult) {
        this.ajaxResult = ajaxResult;
    }

    private String ajaxResult;

    public Object getRespObj() {
        return respObj;
    }

    public void setRespObj(Object respObj) {
        this.respObj = respObj;
    }

    private Object respObj;
}
