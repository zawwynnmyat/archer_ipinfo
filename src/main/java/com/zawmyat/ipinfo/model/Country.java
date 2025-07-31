package com.zawmyat.ipinfo.model;

public class Country {
    public String name;
    public String isoCode;
    public Integer geo_name_id;
    public Integer confidence;
    public City city;

    public Country(String name, String isoCode, Integer geo_name_id, Integer confidence, City city) {
        this.name = name;
        this.isoCode = isoCode;
        this.geo_name_id = geo_name_id;
        this.confidence = confidence;
        this.city = city;
    }
}
