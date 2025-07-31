package com.zawmyat.ipinfo.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeolocationService {

    private DatabaseReader dbReader;
    private DatabaseReader dbCountryReader;
    private DatabaseReader dbAsnReader;

    @PostConstruct
    public void init() throws IOException {
        File database = new File("src/main/resources/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();

        File databaseCountry = new File("src/main/resources/GeoLite2-Country.mmdb");
        dbCountryReader = new DatabaseReader.Builder(databaseCountry).build();

        File databaseASN = new File("src/main/resources/GeoLite2-ASN.mmdb");
        dbAsnReader = new DatabaseReader.Builder(databaseASN).build();
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
