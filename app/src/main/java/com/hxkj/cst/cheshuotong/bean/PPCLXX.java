package com.hxkj.cst.cheshuotong.bean;

/**
 * 品牌车辆信息
 * Created by dell on 2015/11/11.
 */
public class PPCLXX {

    /**
     * CLID : 车辆ID
     * CLMC : 车辆名称
     * CLTB : 车辆图标
     */

    private String CLID;
    private String CLMC;
    private String CLTB;

    public void setCLID(String CLID) {
        this.CLID = CLID;
    }

    public void setCLMC(String CLMC) {
        this.CLMC = CLMC;
    }

    public void setCLTB(String CLTB) {
        this.CLTB = CLTB;
    }

    public String getCLID() {
        return CLID;
    }

    public String getCLMC() {
        return CLMC;
    }

    public String getCLTB() {
        return CLTB;
    }

    @Override
    public String toString() {
        return "PPCLXX{" +
                "CLID='" + CLID + '\'' +
                ", CLMC='" + CLMC + '\'' +
                ", CLTB='" + CLTB + '\'' +
                '}';
    }
}
