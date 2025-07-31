package com.zawmyat.ipinfo.model;

public class Continent {
    public String code;
    public String name;
    public Integer geo_name_id;

    public Continent(String code, String name, Integer geo_name_id) {
        this.code = code;
        this.name = name;
        this.geo_name_id = geo_name_id;
    }
}
