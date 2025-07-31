package com.zawmyat.ipinfo.model;

public class City {
    public String name;
    public  Double latitude;
    public Double longitude;
    public String timezone;
    public Integer population_density;
    public Integer accuracy_radius;
    public Integer metro_code;
    public String postal_code;

    public City(String name, Double latitude, Double longitude, String timezone, Integer population_density, Integer accuracy_radius, Integer metro_code, String postal_code) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.population_density = population_density;
        this.accuracy_radius = accuracy_radius;
        this.metro_code = metro_code;
        this.postal_code = postal_code;
    }
}
