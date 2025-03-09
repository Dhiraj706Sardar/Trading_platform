package com.dtrade.trading.response;



public class AuthResponse {
    private String jwt;
    private boolean status ;
    private String message;
    private boolean isTwoFactAuthEnabled;
    private  String session ;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTwoFactAuthEnabled() {
        return isTwoFactAuthEnabled;
    }

    public void setTwoFactAuthEnabled(boolean twoFactAuthEnabled) {
        isTwoFactAuthEnabled = twoFactAuthEnabled;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
