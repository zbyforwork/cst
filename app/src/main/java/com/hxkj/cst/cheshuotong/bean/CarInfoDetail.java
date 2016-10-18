package com.hxkj.cst.cheshuotong.bean;

/**
 * 车辆详情
 * Created by dell on 2015/11/11.
 */
public class CarInfoDetail {

    /**
     * ID : 86EE1DA2F30F4D5DBB0F37EC02214931
     * BRANDNAME : 江淮
     * FRAMENUM : 123456
     * BRANDICON : /J/20151106122944876_6710/20151106122944876_2007/1446784226734_9437.jpg
     * ICON : /J/20151106122944876_6710/20151106122944876_2007/1446784226734_9437.jpg
     * PLATETYPE : 小型车
     * BIGIMG : /J/20151106122944876_6710/20151106122944876_2007/1446784226734_9437_b.jpg
     * PLATENUMBER : 川A9527
     * MODELS : 江淮K3
     * NATIONNAME : 成都市
     * CREATETIME : 2015-11-10
     * NATIONID : 510100
     * ALIAS : XXX2
     */

    private String ID;
    private String BRANDNAME;
    private String FRAMENUM;
    private String BRANDICON;
    private String ICON;
    private String PLATETYPE;
    private String BIGIMG;
    private String PLATENUMBER;
    private String MODELS;
    private String NATIONNAME;
    private String CREATETIME;
    private String NATIONID;
    private String ALIAS;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setBRANDNAME(String BRANDNAME) {
        this.BRANDNAME = BRANDNAME;
    }

    public void setFRAMENUM(String FRAMENUM) {
        this.FRAMENUM = FRAMENUM;
    }

    public void setBRANDICON(String BRANDICON) {
        this.BRANDICON = BRANDICON;
    }

    public void setICON(String ICON) {
        this.ICON = ICON;
    }

    public void setPLATETYPE(String PLATETYPE) {
        this.PLATETYPE = PLATETYPE;
    }

    public void setBIGIMG(String BIGIMG) {
        this.BIGIMG = BIGIMG;
    }

    public void setPLATENUMBER(String PLATENUMBER) {
        this.PLATENUMBER = PLATENUMBER;
    }

    public void setMODELS(String MODELS) {
        this.MODELS = MODELS;
    }

    public void setNATIONNAME(String NATIONNAME) {
        this.NATIONNAME = NATIONNAME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public void setNATIONID(String NATIONID) {
        this.NATIONID = NATIONID;
    }

    public void setALIAS(String ALIAS) {
        this.ALIAS = ALIAS;
    }

    public String getID() {
        return ID;
    }

    public String getBRANDNAME() {
        return BRANDNAME;
    }

    public String getFRAMENUM() {
        return FRAMENUM;
    }

    public String getBRANDICON() {
        return BRANDICON;
    }

    public String getICON() {
        return ICON;
    }

    public String getPLATETYPE() {
        return PLATETYPE;
    }

    public String getBIGIMG() {
        return BIGIMG;
    }

    public String getPLATENUMBER() {
        return PLATENUMBER;
    }

    public String getMODELS() {
        return MODELS;
    }

    public String getNATIONNAME() {
        return NATIONNAME;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public String getNATIONID() {
        return NATIONID;
    }

    public String getALIAS() {
        return ALIAS;
    }

    @Override
    public String toString() {
        return "CarInfoDetail{" +
                "ID='" + ID + '\'' +
                ", BRANDNAME='" + BRANDNAME + '\'' +
                ", FRAMENUM='" + FRAMENUM + '\'' +
                ", BRANDICON='" + BRANDICON + '\'' +
                ", ICON='" + ICON + '\'' +
                ", PLATETYPE='" + PLATETYPE + '\'' +
                ", BIGIMG='" + BIGIMG + '\'' +
                ", PLATENUMBER='" + PLATENUMBER + '\'' +
                ", MODELS='" + MODELS + '\'' +
                ", NATIONNAME='" + NATIONNAME + '\'' +
                ", CREATETIME='" + CREATETIME + '\'' +
                ", NATIONID='" + NATIONID + '\'' +
                ", ALIAS='" + ALIAS + '\'' +
                '}';
    }
}
