package com.hxkj.cst.cheshuotong.model;

import java.util.List;

/**
 * Created by 刘杨 on 2015/10/16 11:29.
 * 省份类
 */
public class Province {
    /**
     * 省份名
     */
    private String name;
    /**
     * 市集合
     */
    private List<String> cities;
    /**
     * 是否直辖市
     */
    private boolean municipality;

    public Province() {
    }

    public Province(String name, List<String> cities, boolean municipality) {
        this.name = name;
        this.cities = cities;
        this.municipality = municipality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public boolean isMunicipality() {
        return municipality;
    }

    public void setMunicipality(boolean municipality) {
        this.municipality = municipality;
    }
}
