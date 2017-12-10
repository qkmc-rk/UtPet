package org.arc.ytpet.core.model;

public class Pet {
    private Integer petid;

    private Integer fCustomid;

    private String pettype;

    private String petname;

    private String petimage;

    public Integer getPetid() {
        return petid;
    }

    public void setPetid(Integer petid) {
        this.petid = petid;
    }

    public Integer getfCustomid() {
        return fCustomid;
    }

    public void setfCustomid(Integer fCustomid) {
        this.fCustomid = fCustomid;
    }

    public String getPettype() {
        return pettype;
    }

    public void setPettype(String pettype) {
        this.pettype = pettype == null ? null : pettype.trim();
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname == null ? null : petname.trim();
    }

    public String getPetimage() {
        return petimage;
    }

    public void setPetimage(String petimage) {
        this.petimage = petimage == null ? null : petimage.trim();
    }
}