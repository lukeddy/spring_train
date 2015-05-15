package com.server.model;

import com.server.util.DateUtils;

import java.io.Serializable;

/**
 * 系统用户封装类
 */
public class User implements Serializable {

    private int id;
    private String username;
    private String pwd;
    private int role;
    private int lockState;
    private long lastLoginTime;
    private String lastLoginTimeFormated;

    public String getLastLoginTimeFormated() {
        return  this.lastLoginTime>0? DateUtils.formatDate(DateUtils.DATE_YYYY_MM_DD_HH_MM_SS,this.lastLoginTime):"";
    }

    public void setLastLoginTimeFormated(String lastLoginTimeFormated) {
        this.lastLoginTimeFormated = lastLoginTimeFormated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getLockState() {
        return lockState;
    }

    public void setLockState(int lockState) {
        this.lockState = lockState;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
