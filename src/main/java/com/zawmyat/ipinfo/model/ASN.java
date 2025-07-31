package com.zawmyat.ipinfo.model;

public class ASN {

    public Integer number;
    public String name;
    public String hostAddress;
    public Integer prefixLength;

    public ASN(Integer number, String name, String hostAddress, Integer prefixLength) {
        this.number = number;
        this.name = name;
        this.hostAddress = hostAddress;
        this.prefixLength = prefixLength;
    }

}
