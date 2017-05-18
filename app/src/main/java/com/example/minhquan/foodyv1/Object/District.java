package com.example.minhquan.foodyv1.Object;

import java.util.List;

/**
 * Created by MinhQuan on 4/18/2017.
 */

public class District {

    private Integer id;
    private String name;
    private List<Street> streetList;
    private Integer cityId;

    public District(){}

    public District(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<Street> streetList) {
        this.streetList = streetList;
    }
}
