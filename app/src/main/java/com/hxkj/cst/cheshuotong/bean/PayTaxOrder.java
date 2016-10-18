package com.hxkj.cst.cheshuotong.bean;

/**
 * Created by dell on 2015/11/11.
 */
public class PayTaxOrder {


    /**
     * DDID : 订单id
     * DDBH : 订单编号
     * ZTMS : 申报状态描述
     * QYMC : 申报区域名称
     * CJSJ : 订单创建时间
     * CLTP : 订单车辆图片
     * CLMC : 订单车辆名称
     */

    private String DDID;
    private String DDBH;
    private String ZTMS;
    private String QYMC;
    private String CJSJ;
    private String CLTP;
    private String CLMC;
    private String STATUS;

    public void setDDID(String DDID) {
        this.DDID = DDID;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public void setZTMS(String ZTMS) {
        this.ZTMS = ZTMS;
    }

    public void setQYMC(String QYMC) {
        this.QYMC = QYMC;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public void setCLTP(String CLTP) {
        this.CLTP = CLTP;
    }

    public void setCLMC(String CLMC) {
        this.CLMC = CLMC;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getDDID() {
        return DDID;
    }

    public String getDDBH() {
        return DDBH;
    }

    public String getZTMS() {
        return ZTMS;
    }

    public String getQYMC() {
        return QYMC;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public String getCLTP() {
        return CLTP;
    }

    public String getCLMC() {
        return CLMC;
    }

    @Override
    public String toString() {
        return "PayTaxOrder{" +
                "DDID='" + DDID + '\'' +
                ", DDBH='" + DDBH + '\'' +
                ", ZTMS='" + ZTMS + '\'' +
                ", QYMC='" + QYMC + '\'' +
                ", CJSJ='" + CJSJ + '\'' +
                ", CLTP='" + CLTP + '\'' +
                ", CLMC='" + CLMC + '\'' +
                ", STATUS='" + STATUS + '\'' +
                '}';
    }
}
