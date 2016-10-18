package com.hxkj.cst.cheshuotong.bean;

/**
 * 税务机构
 * Created by 刘杨 on 2015/10/29 15:12.
 */
public class SWJG {

    /**
     * LXDH : 85060922、85068773
     * JGDZ : 成都市武候区红牌楼长益路8号
     * JGMC : 成都市国家税务局车辆购置税征收处
     * JGDM : 5101070001
     */

    private String LXDH;
    private String JGDZ;
    private String JGMC;
    private String JGDM;

    public void setLXDH(String LXDH) {
        this.LXDH = LXDH;
    }

    public void setJGDZ(String JGDZ) {
        this.JGDZ = JGDZ;
    }

    public void setJGMC(String JGMC) {
        this.JGMC = JGMC;
    }

    public void setJGDM(String JGDM) {
        this.JGDM = JGDM;
    }

    public String getLXDH() {
        return LXDH;
    }

    public String getJGDZ() {
        return JGDZ;
    }

    public String getJGMC() {
        return JGMC;
    }

    public String getJGDM() {
        return JGDM;
    }

    @Override
    public String toString() {
        return "SWJG{" +
                "LXDH='" + LXDH + '\'' +
                ", JGDZ='" + JGDZ + '\'' +
                ", JGMC='" + JGMC + '\'' +
                ", JGDM='" + JGDM + '\'' +
                '}';
    }
}
