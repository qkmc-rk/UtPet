package org.arc.ytpet.core.model;

import java.util.Date;

public class Charge {
    private Integer chargeid;

    private Integer fCustomid;

    private Date chargetime;

    private Double amount;

    public Integer getChargeid() {
        return chargeid;
    }

    public void setChargeid(Integer chargeid) {
        this.chargeid = chargeid;
    }

    public Integer getfCustomid() {
        return fCustomid;
    }

    public void setfCustomid(Integer fCustomid) {
        this.fCustomid = fCustomid;
    }

    public Date getChargetime() {
        return chargetime;
    }

    public void setChargetime(Date chargetime) {
        this.chargetime = chargetime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}