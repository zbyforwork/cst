package com.hxkj.cst.cheshuotong.bean;

/**
 * Created by dell on 2015/11/10.
 */
public class User {

    /**
     * PHONE : 13826458625
     * NAME : 222
     * AGE : 22
     * SEX : 22
     * ID : 2222
     * CREATETIME : 2015-11-04 17:45:43
     * PASSWORD : xxx
     * IDCARD : 22222rett
     */

    private String PHONE;
    private String NAME;
    private String AGE;
    private String SEX;
    private String ID;
    private String CREATETIME;
    private String PASSWORD;
    private String IDCARD;

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getAGE() {
        return AGE;
    }

    public String getSEX() {
        return SEX;
    }

    public String getID() {
        return ID;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    @Override
    public String toString() {
        return "User{" +
                "PHONE='" + PHONE + '\'' +
                ", NAME='" + NAME + '\'' +
                ", AGE='" + AGE + '\'' +
                ", SEX='" + SEX + '\'' +
                ", ID='" + ID + '\'' +
                ", CREATETIME='" + CREATETIME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", IDCARD='" + IDCARD + '\'' +
                '}';
    }
}
