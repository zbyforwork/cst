package com.hxkj.cst.cheshuotong.bean;

import java.lang.Override;
import java.lang.String;

/**
 * 车辆信息
 * Created by 刘杨 on 2015/10/26 11:24.
 */
public class CLXX {

    /**
     * ID : 1
     * PPMC : 品牌-名称
     * XLCS : 车系-厂商
     * XLMC : 车系-名称
     * XHNK : 车型-年款
     * XHMC : 车型-名称
     * PPGJ : 品牌-国家
     * PPJS : 品牌-技术
     * XLJB : 车系-级别
     */

    private String ID;
    private String PPMC;
    private String XLCS;
    private String XLMC;
    private String XHNK;
    private String XHMC;
    private String PPGJ;
    private String PPJS;
    private String XLJB;
    private String sortLetters;//显示数据拼音的首字母
    /**
     * PPTPDZ : /usr/share/cstapp/audi.png
     * CLTPDZ : /usr/share/cstapp/audi_A6L.jpg
     */

    private String PPTPDZ;
    private String CLTPDZ;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPPMC(String PPMC) {
        this.PPMC = PPMC;
    }

    public void setXLCS(String XLCS) {
        this.XLCS = XLCS;
    }

    public void setXLMC(String XLMC) {
        this.XLMC = XLMC;
    }

    public void setXHNK(String XHNK) {
        this.XHNK = XHNK;
    }

    public void setXHMC(String XHMC) {
        this.XHMC = XHMC;
    }

    public void setPPGJ(String PPGJ) {
        this.PPGJ = PPGJ;
    }

    public void setPPJS(String PPJS) {
        this.PPJS = PPJS;
    }

    public void setXLJB(String XLJB) {
        this.XLJB = XLJB;
    }

    public String getID() {
        return ID;
    }

    public String getPPMC() {
        return PPMC;
    }

    public String getXLCS() {
        return XLCS;
    }

    public String getXLMC() {
        return XLMC;
    }

    public String getXHNK() {
        return XHNK;
    }

    public String getXHMC() {
        return XHMC;
    }

    public String getPPGJ() {
        return PPGJ;
    }

    public String getPPJS() {
        return PPJS;
    }

    public String getXLJB() {
        return XLJB;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public void setPPTPDZ(String PPTPDZ) {
        this.PPTPDZ = PPTPDZ;
    }

    public void setCLTPDZ(String CLTPDZ) {
        this.CLTPDZ = CLTPDZ;
    }

    public String getPPTPDZ() {
        return PPTPDZ;
    }

    public String getCLTPDZ() {
        return CLTPDZ;
    }

    @Override
    public String toString() {
        return "CLXX{" +
                "ID=" + ID +
                ", PPMC=" + PPMC +
                ", XLCS=" + XLCS +
                ", XLMC=" + XLMC +
                ", XHNK=" + XHNK +
                ", XHMC=" + XHMC +
                ", PPGJ=" + PPGJ +
                ", PPJS=" + PPJS +
                ", XLJB=" + XLJB +
                ", sortLetters=" + sortLetters +
                ", PPTPDZ=" + PPTPDZ +
                ", CLTPDZ=" + CLTPDZ +
                '}';
    }
}
