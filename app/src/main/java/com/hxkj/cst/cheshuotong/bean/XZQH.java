package com.hxkj.cst.cheshuotong.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 行政区划
 * Created by Liuyang on 2015/10/25.
 */
public class XZQH implements Serializable {

    /**
     * SJDM : 000000
     * XZQHDM : 110000
     * XZQHMC : 北京市
     */

    private String XZQHDM;
    private String XZQHMC;

    public ArrayList<XZQH> getXSXZQH() {
        return XSXZQH;
    }

    public void setXSXZQH(ArrayList<XZQH> XSXZQH) {
        this.XSXZQH = XSXZQH;
    }

    private ArrayList<XZQH> XSXZQH;

    public void setXZQHDM(String XZQHDM) {
        this.XZQHDM = XZQHDM;
    }

    public void setXZQHMC(String XZQHMC) {
        this.XZQHMC = XZQHMC;
    }


    public String getXZQHDM() {
        return XZQHDM;
    }

    public String getXZQHMC() {
        return XZQHMC;
    }

    @Override
    public String toString() {
        return "XZQH{" +
                "XZQHDM='" + XZQHDM + '\'' +
                ", XZQHMC='" + XZQHMC + '\'' +
                ", XSXZQH=" + XSXZQH +
                '}';
    }
}
