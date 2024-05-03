package org.switf.lugares.services;

import org.switf.lugares.request.PlaceRequest;
import org.switf.lugares.response.PlaceResponse;


import java.util.List;
import java.util.Optional;


public interface PlaceService {
    PlaceResponse createPlace(PlaceRequest place);

    List<PlaceResponse> getAllPlaces();

    Optional<PlaceResponse> getPlaceById(Integer idPlace);

    PlaceResponse updatePlaceByID(Integer idPlace, PlaceRequest place);

    boolean deletePlaceByID(Integer id);

    PlaceResponse savePlaceUrlImage(Integer idPlace, PlaceRequest request);

    boolean existsById(Integer idPlace);
}
