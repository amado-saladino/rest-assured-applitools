package tau.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String postCode;
    private String country;
    private String countryAbbreviation;
    private List<Place> places;

    public Location() {
    }

    public Location(String code, String country, String abbreviation) {
        postCode = code;
        this.country = country;
        countryAbbreviation = abbreviation;
    }

    public void addPlace(Place place) {
        if (places==null) places = new ArrayList<>();
        places.add(place);
    }

    @JsonProperty("post code")
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty("post code")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("country abbreviation")
    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    @JsonProperty("country abbreviation")
    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }
}
