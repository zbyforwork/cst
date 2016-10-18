package com.hxkj.cst.cheshuotong.bean;

/**
 * Created by dell on 2015/11/11.
 */
public class CarInfoSimple{

    /**
     * BRANDNAME : 江淮
     * FRAMENUM : 123456
     * BRANDICON : /J/20151106122944876_6710/20151106122944876_2007/1446784226734_9437.jpg
     * ICON : /J/20151106122944876_6710/20151106122944876_2007/1446784226734_9437.jpg
     * PLATETYPE : 小型车
     * ID : 86EE1DA2F30F4D5DBB0F37EC02214931
     * PLATENUMBER : 川A9527
     * MODELS : 江淮K3
     * NATIONNAME : 成都市
     * ALIAS : XXX2
     */

    private String BRANDNAME;
    private String FRAMENUM;
    private String BRANDICON;
    private String ICON;
    private String PLATETYPE;
    private String ID;
    private String PLATENUMBER;
    private String MODELS;
    private String NATIONNAME;
    private String ALIAS;

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

    public void setID(String ID) {
        this.ID = ID;
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

    public void setALIAS(String ALIAS) {
        this.ALIAS = ALIAS;
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

    public String getID() {
        return ID;
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

    public String getALIAS() {
        return ALIAS;
    }

    @Override
    public String toString() {
        return "CarInfoSimple{" +
                "BRANDNAME='" + BRANDNAME + '\'' +
                ", FRAMENUM='" + FRAMENUM + '\'' +
                ", BRANDICON='" + BRANDICON + '\'' +
                ", ICON='" + ICON + '\'' +
                ", PLATETYPE='" + PLATETYPE + '\'' +
                ", ID='" + ID + '\'' +
                ", PLATENUMBER='" + PLATENUMBER + '\'' +
                ", MODELS='" + MODELS + '\'' +
                ", NATIONNAME='" + NATIONNAME + '\'' +
                ", ALIAS='" + ALIAS + '\'' +
                '}';
    }
}
