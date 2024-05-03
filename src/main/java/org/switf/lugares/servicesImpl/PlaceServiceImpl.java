package org.switf.lugares.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switf.lugares.models.PlaceModel;
import org.switf.lugares.repositories.LikeDislikeJpaRepository;
import org.switf.lugares.repositories.PlaceJpaRepository;
import org.switf.lugares.request.PlaceRequest;
import org.switf.lugares.response.PlaceResponse;
import org.switf.lugares.services.PlaceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceJpaRepository placeRepository;

    @Autowired
    private LikeDislikeJpaRepository likeDislikeRepository;

    @Override
    public PlaceResponse createPlace(PlaceRequest placeRequest) {
        PlaceModel placeModel = new PlaceModel();
        placeModel.setIdPlace(null);
        placeModel.setName(placeRequest.getName());
        placeModel.setAddressCity(placeRequest.getAddressCity());
        placeModel.setAddressStreet(placeRequest.getAddressStreet());
        placeModel.setAddressState(placeRequest.getAddressState());
        placeModel.setAddressColonia(placeRequest.getAddressColonia());
        placeModel.setAddressZipcode(placeRequest.getAddressZipcode());
        placeModel.setDescription(placeRequest.getDescription());
        placeModel.setUrl(placeRequest.getUrl());

        PlaceModel newPlaceModel = placeRepository.save(placeModel);
        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setIdPlace(newPlaceModel.getIdPlace());
        placeResponse.setName(newPlaceModel.getName());
        placeResponse.setAddressCity(newPlaceModel.getAddressCity());
        placeResponse.setAddressState(newPlaceModel.getAddressState());
        placeResponse.setAddressColonia(newPlaceModel.getAddressColonia());
        placeResponse.setAddressStreet(newPlaceModel.getAddressStreet());
        placeResponse.setAddressZipcode(newPlaceModel.getAddressZipcode());
        placeResponse.setDescription(newPlaceModel.getDescription());
        placeResponse.setUrl(newPlaceModel.getUrl());

        return placeResponse;
    }

    @Override
    public List<PlaceResponse> getAllPlaces() {
        List<PlaceResponse> results = new ArrayList<PlaceResponse>();
        for (PlaceModel placeModel: placeRepository.findAll()) {
            PlaceResponse placeResponse = new PlaceResponse();
            placeResponse.setIdPlace(placeModel.getIdPlace());
            placeResponse.setName(placeModel.getName());
            placeResponse.setAddressCity(placeModel.getAddressCity());
            placeResponse.setAddressState(placeModel.getAddressState());
            placeResponse.setAddressColonia(placeModel.getAddressColonia());
            placeResponse.setAddressStreet(placeModel.getAddressStreet());
            placeResponse.setAddressZipcode(placeModel.getAddressZipcode());
            placeResponse.setDescription(placeModel.getDescription());
            placeResponse.setUrl(placeModel.getUrl());
            results.add(placeResponse);
        }
        return results;
    }

    @Override
    public Optional<PlaceResponse> getPlaceById(Integer idPlace) {
        Optional<PlaceModel> placeOptional = placeRepository.findById(idPlace);

        if (placeOptional.isPresent()) {
            PlaceModel placeModel = placeOptional.get();
            PlaceResponse placeResponse = new PlaceResponse();

            placeResponse.setIdPlace(placeModel.getIdPlace());
            placeResponse.setName(placeModel.getName());
            placeResponse.setDescription(placeModel.getDescription());
            placeResponse.setAddressState(placeModel.getAddressState());
            placeResponse.setAddressCity(placeModel.getAddressCity());
            placeResponse.setAddressColonia(placeModel.getAddressColonia());
            placeResponse.setAddressStreet(placeModel.getAddressStreet());
            placeResponse.setAddressZipcode(placeModel.getAddressZipcode());
            placeResponse.setUrl(placeModel.getUrl());
            return Optional.of(placeResponse);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PlaceResponse updatePlaceByID(Integer idPlace, PlaceRequest place) {
            PlaceModel placeModel = placeRepository.getReferenceById(idPlace);
            placeModel.setIdPlace(idPlace);
            placeModel.setName(place.getName());
            placeModel.setAddressCity(place.getAddressCity());
            placeModel.setAddressStreet(place.getAddressStreet());
            placeModel.setAddressState(place.getAddressState());
            placeModel.setAddressColonia(place.getAddressColonia());
            placeModel.setAddressZipcode(place.getAddressZipcode());
            placeModel.setDescription(place.getDescription());

            PlaceModel newPlaceModel = placeRepository.save(placeModel);
            PlaceResponse placeResponse = new PlaceResponse();
            placeResponse.setIdPlace(newPlaceModel.getIdPlace());
            placeResponse.setName(newPlaceModel.getName());
            placeResponse.setAddressCity(newPlaceModel.getAddressCity());
            placeResponse.setAddressState(newPlaceModel.getAddressState());
            placeResponse.setAddressColonia(newPlaceModel.getAddressColonia());
            placeResponse.setAddressStreet(newPlaceModel.getAddressStreet());
            placeResponse.setAddressZipcode(newPlaceModel.getAddressZipcode());
            placeResponse.setDescription(newPlaceModel.getDescription());

            return placeResponse;
    }

    @Override
    public boolean deletePlaceByID(Integer id) {
        Optional<PlaceModel> placeOptional = placeRepository.findById(id);
        if (placeOptional.isPresent()) {
            placeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PlaceResponse savePlaceUrlImage(Integer idPlace, PlaceRequest placeRequestReceived) {
        String imageUrlReceived = placeRequestReceived.getUrl();
        if (placeRepository.existsById(idPlace))
            placeRepository.findById(idPlace).map(placeFound -> {
                placeFound.setIdPlace(idPlace);
                placeFound.setName(placeFound.getName());
                placeFound.setAddressCity(placeFound.getAddressCity());
                placeFound.setAddressStreet(placeFound.getAddressStreet());
                placeFound.setAddressState(placeFound.getAddressState());
                placeFound.setAddressColonia(placeFound.getAddressColonia());
                placeFound.setAddressZipcode(placeFound.getAddressZipcode());
                placeFound.setDescription(placeFound.getDescription());
                placeFound.setUrl(imageUrlReceived);
                return placeRepository.save(placeFound);
            });
        PlaceModel placeModel = placeRepository.getReferenceById(idPlace);
        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setIdPlace(placeModel.getIdPlace());
        placeResponse.setName(placeModel.getName());
        placeResponse.setAddressCity(placeModel.getAddressCity());
        placeResponse.setAddressState(placeModel.getAddressState());
        placeResponse.setAddressColonia(placeModel.getAddressColonia());
        placeResponse.setAddressStreet(placeModel.getAddressStreet());
        placeResponse.setAddressZipcode(placeModel.getAddressZipcode());
        placeResponse.setDescription(placeModel.getDescription());
        placeResponse.setUrl(placeModel.getUrl());
        return placeResponse;
    }

    @Override
    public boolean existsById(Integer idPlace) {
        Optional<PlaceModel> placeOptional = placeRepository.findById(idPlace);
        return placeOptional.isPresent(); // Devuelve true si el lugar existe, false si no existe
    }


//    public PlaceResponse getSecurePlaces(Integer idPlace) {
//
//        PlaceModel placeModel = placeRepository.getReferenceById(idPlace);
//        PlaceResponse placeResponse = new PlaceResponse();
//
//        LikeDislikeModel likeDislikeModel = likeDislikeRepository.findByPlace(idPlace);
//        double safetyScore = likeDislikeModel.get
//
//        // Considerar seguro si el puntaje es mayor o igual a 0.5
//        if (safetyScore >= 0.5) {
//            securePlaces.add(place);
//        }
//        return securePlaces;
//    }


}





