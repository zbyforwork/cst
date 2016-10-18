package com.hxkj.cst.cheshuotong.bean;

/**
 * 档案信息
 * Created by dell on 2015/11/13.
 */
public class DAXX {

    /**
     * DADZ : 档案图片存放地址1
     * DALB : 档案类别1
     * DAMC : 档案名称1
     */

    private String DADZ;
    private String DALB;
    private String DAMC;

    public void setDADZ(String DADZ) {
        this.DADZ = DADZ;
    }

    public void setDALB(String DALB) {
        this.DALB = DALB;
    }

    public void setDAMC(String DAMC) {
        this.DAMC = DAMC;
    }

    public String getDADZ() {
        return DADZ;
    }

    public String getDALB() {
        return DALB;
    }

    public String getDAMC() {
        return DAMC;
    }

    @Override
    public String toString() {
        return "DAXX{" +
                "DADZ='" + DADZ + '\'' +
                ", DALB='" + DALB + '\'' +
                ", DAMC='" + DAMC + '\'' +
                '}';
    }
}
