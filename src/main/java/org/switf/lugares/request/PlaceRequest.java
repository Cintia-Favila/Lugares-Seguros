package org.switf.lugares.request;
import lombok.*;

@Data
public class PlaceRequest {
    private String name;
    private String description;
    private String addressState;
    private String addressCity;
    private String addressColonia;
    private String addressStreet;
    private String addressZipcode;
    private String url;
}
