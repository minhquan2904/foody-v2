package com.example.minhquan.foodyv1.Object;

import java.io.Serializable;

/**
 * Created by MinhQuan on 4/19/2017.
 */

public class Category implements Serializable {
    private int id;
    private String name;
    private String image;

    public Category() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
