package com.example.minhquan.foodyv1.Object;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class City implements Serializable {
    private Integer id;
    private String name;
    private List<District> districts;
    private List<Street> streets;

    public City(){}
    public City(Integer id, String name){
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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }
}
