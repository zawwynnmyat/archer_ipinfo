package com.zawmyat.ipinfo.model;

public class Geolocation {

    public String ip;
    public String region;
    public Continent continent;
    public Country country;
    public ASN asn;

    public Geolocation(String ip, String region, Continent continent, Country country, ASN asn) {
        this.ip = ip;
        this.region = region;
        this.continent = continent;
        this.country = country;
        this.asn = asn;
    }
}
