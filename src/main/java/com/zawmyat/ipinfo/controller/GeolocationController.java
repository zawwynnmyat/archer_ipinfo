package com.zawmyat.ipinfo.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.zawmyat.ipinfo.exception.ApiRequestException;
import com.zawmyat.ipinfo.model.*;
import com.zawmyat.ipinfo.service.GeolocationService;
import com.zawmyat.ipinfo.utils.IPUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class GeolocationController {

    private final GeolocationService service;

    public GeolocationController(GeolocationService service) {
        this.service = service;
    }

    @Tag(name = "GET", description = "GET methods of Archer IP Info API")
    @Operation(
            summary = "Get an IP Address's detail information.",
            description = "Provide an IP Address as request parameter."
    )
    @GetMapping("/api/ipinfo")
    public Geolocation getGeoData(@RequestParam String ip) throws Exception {

        try {

            CityResponse response = service.getLocation(ip);
            CountryResponse responseCountry = service.getCountry(ip);
            AsnResponse responseAsn = service.getASN(ip);

            Continent continent = new Continent(
                    responseCountry.getContinent().getCode(),
                    responseCountry.getContinent().getName(),
                    responseCountry.getContinent().getGeoNameId()
            );

            ASN asn = new ASN(
                    responseAsn.getAutonomousSystemNumber(),
                    responseAsn.getAutonomousSystemOrganization(),
                    responseAsn.getNetwork().getNetworkAddress().getHostAddress(),
                    responseAsn.getNetwork().getPrefixLength()
            );

            City city = new City(
                    response.getCity().getName(),
                    response.getLocation().getLatitude(),
                    response.getLocation().getLongitude(),
                    response.getLocation().getTimeZone(),
                    response.getLocation().getPopulationDensity(),
                    response.getLocation().getAccuracyRadius(),
                    response.getLocation().getMetroCode(),
                    response.getPostal().getCode()
            );

            Country country = new Country(
                    responseCountry.getCountry().getName(),
                    responseCountry.getCountry().getIsoCode(),
                    responseCountry.getCountry().getGeoNameId(),
                    responseCountry.getCountry().getConfidence(),
                    city
            );

            return new Geolocation(
                    responseAsn.getIpAddress(),
                    response.getMostSpecificSubdivision().getName(),
                    continent,
                    country,
                    asn
            );


        } catch (Exception e) {
            throw new ApiRequestException("Opps! cannot get that IP address's details.");
        }

    }

    @Operation(
            summary = "Get your current IP address in JSON and text format.",
            description = "The response is a String or JSON type of your IP Address."
    )
    @Tag(name = "GET", description = "GET methods of Archer IP Info API")
    @GetMapping("/myip")
    public Object getMyIpJson(@RequestParam IPRequestParam format, HttpServletRequest request) throws IOException {
        String clientIp = IPUtil.getClientIP(request);

        if(format.equals(IPRequestParam.json)){
            return new IPAddress(
                    clientIp
            );
        } else {
            return clientIp;
        }
    }

    @Operation(
            summary = "Root route - user's current IP address",
            description = "The response is a JSON type of your IP Address."
    )
    @Tag(name = "GET", description = "GET methods of Archer IP Info API")
    @GetMapping()
    public IPAddress getMyIp(HttpServletRequest request) throws IOException {
        String clientIp = IPUtil.getClientIP(request);

        return new IPAddress(
                clientIp
        );

    }

}
