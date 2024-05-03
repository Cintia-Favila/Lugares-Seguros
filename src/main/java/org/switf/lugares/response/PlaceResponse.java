package org.switf.lugares.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class PlaceResponse {

    private Integer idPlace;
    private String name;
    private String addressState;
    private String addressCity;
    private String addressColonia;
    private String addressStreet;
    private String addressZipcode;
    private String description;
    private String url;

}
