package org.arc.ytpet.core.model.databind;

public class PrimInfoRecord {

    private  Integer recordId;

    private Integer customId;

    private String customName;

    private String payWhat;

    private Double amount;

    private String mark;

    //setters and getters

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getPayWhat() {
        return payWhat;
    }

    public void setPayWhat(String payWhat) {
        this.payWhat = payWhat;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
