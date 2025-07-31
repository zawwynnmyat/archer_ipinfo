# IP Info API

Detail information of the IP addresses you entered and your IP address

## Endpoints:

> Your IP Address in JSON
http://localhost:8080/myip?format=json

Response:
```JSON
{
  "ip": "37.111.53.252"
}
```

> IP Address Information
http://localhost:8080/api/ipinfo?ip=[YOUR_IP_ADDRESS]

Response:
```JSON
{
  "ip": "37.111.53.252",
  "region": null,
  "continent": {
    "code": "AS",
    "name": "Asia",
    "geo_name_id": 6255147
  },
  "country": {
    "name": "Myanmar",
    "isoCode": "MM",
    "geo_name_id": 1327865,
    "confidence": null,
    "city": {
      "name": null,
      "latitude": 21,
      "longitude": 96,
      "timezone": "Asia/Yangon",
      "population_density": null,
      "accuracy_radius": 1000,
      "metro_code": null,
      "postal_code": null
    }
  },
  "asn": {
    "number": 133385,
    "name": "Atom Myanmar Limited",
    "hostAddress": "37.111.52.0",
    "prefixLength": 23
  }
}
```

> Your IP Address in String
http://localhost:8080

OR

http://localhost:8080/myip?format=text
