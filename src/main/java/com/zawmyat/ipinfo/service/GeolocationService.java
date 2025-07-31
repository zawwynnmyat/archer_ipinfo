package com.zawmyat.ipinfo.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

@Service
public class GeolocationService {

    private DatabaseReader dbReader;
    private DatabaseReader dbCountryReader;
    private DatabaseReader dbAsnReader;

    @PostConstruct
    public void init() throws IOException {
        InputStream cityStream = getClass().getResourceAsStream("/GeoLite2-City.mmdb");
        InputStream countryStream = getClass().getResourceAsStream("/GeoLite2-Country.mmdb");
        InputStream asnStream = getClass().getResourceAsStream("/GeoLite2-ASN.mmdb");

        if (cityStream == null || countryStream == null || asnStream == null) {
            throw new FileNotFoundException("One or more GeoLite2 database files not found in resources.");
        }

        dbReader = new DatabaseReader.Builder(cityStream).build();
        dbCountryReader = new DatabaseReader.Builder(countryStream).build();
        dbAsnReader = new DatabaseReader.Builder(asnStream).build();
    }

    public CityResponse getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);

        return dbReader.city(ipAddress);
    }

    public CountryResponse getCountry(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        return dbCountryReader.country(ipAddress);
    }

    public AsnResponse getASN(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        return dbAsnReader.asn(ipAddress);
    }

}
