package org.arc.ytpet.core.model;

import java.util.Date;

public class Balance {
    private Integer blcid;

    private Double balance;

    private Integer fCustomid;

    private Date updtime;

    public Integer getBlcid() {
        return blcid;
    }

    public void setBlcid(Integer blcid) {
        this.blcid = blcid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getfCustomid() {
        return fCustomid;
    }

    public void setfCustomid(Integer fCustomid) {
        this.fCustomid = fCustomid;
    }

    public Date getUpdtime() {
        return updtime;
    }

    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }
}