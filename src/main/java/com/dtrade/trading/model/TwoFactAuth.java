package com.dtrade.trading.model;

import com.dtrade.trading.domain.VerificationType;


public class TwoFactAuth {
    private  boolean isEnabled=false;

    private VerificationType sendTo;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public VerificationType getSendTo() {
        return sendTo;
    }

    public void setSendTo(VerificationType sendTo) {
        this.sendTo = sendTo;
    }
}
