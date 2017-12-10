package org.arc.ytpet.core.model;

import java.util.Date;

public class Record {
    private Integer recordid;

    private Integer fCustomid;

    private Date paytime;

    private String paywhat;

    private Double amount;

    private String remark;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getfCustomid() {
        return fCustomid;
    }

    public void setfCustomid(Integer fCustomid) {
        this.fCustomid = fCustomid;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPaywhat() {
        return paywhat;
    }

    public void setPaywhat(String paywhat) {
        this.paywhat = paywhat == null ? null : paywhat.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}