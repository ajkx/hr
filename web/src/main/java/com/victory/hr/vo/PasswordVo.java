package com.victory.hr.vo;

/**
 * Created by ajkx
 * Date: 2016/12/28.
 * Time:20:44
 */
public class PasswordVo {
    
    private String currentPassword;
    private String password;
    private String confirmPassword;

    public PasswordVo() {
    }

    public PasswordVo(String currentPassword, String password, String confirmPassword) {
        this.currentPassword = currentPassword;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
