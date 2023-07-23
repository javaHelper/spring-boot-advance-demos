package com.example.speldemoxml.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shipping {
    private Map<String, List<City>> locationsByCountry;
    private Map<String, List<City>> chargesByLocation;

    public Shipping() {
        List<City> usCities = new ArrayList<>();
        usCities.add(new City("Alabama",8.50,false));
        usCities.add(new City("New Jersey",10.50,false));
        usCities.add(new City("New York",10.50,false));
        usCities.add(new City("Washington",10.50,true));

        List<City> ukCities = new ArrayList<>();
        ukCities.add(new City("London",25.50,true));
        ukCities.add(new City("Cambridge",20.50,false));
        ukCities.add(new City("Leeds",15.50,false));

        List<City> dkCities = new ArrayList<>();
        dkCities.add(new City("Copenhagen",20.50,true));
        dkCities.add(new City("Hadsund",12.50,false));
        dkCities.add(new City("Arden",14.50,false));

        List<City> myCities = new ArrayList<>();
        myCities.add(new City("Kuala Lumpur",5.50,true));
        myCities.add(new City("Johor Bahru",3.50,false));

        this.locationsByCountry = new HashMap<>();
        this.locationsByCountry.put("US",usCities);
        this.locationsByCountry.put("UK",ukCities);
        this.locationsByCountry.put("DK",dkCities);
        this.locationsByCountry.put("MY",myCities);

        this.chargesByLocation = new HashMap<>();
        this.chargesByLocation.put("US", usCities);
        this.chargesByLocation.put("UK", ukCities);
        this.chargesByLocation.put("DK", dkCities);
        this.chargesByLocation.put("MY", myCities);
    }

    public Map<String, List<City>> getLocationsByCountry() {
        return locationsByCountry;
    }

    public void setLocationsByCountry(Map<String, List<City>> locationsByCountry) {
        this.locationsByCountry = locationsByCountry;
    }

    public Map<String, List<City>> getChargesByLocation() {
        return chargesByLocation;
    }

    public void setChargesByLocation(Map<String, List<City>> chargesByLocation) {
        this.chargesByLocation = chargesByLocation;
    }
}
