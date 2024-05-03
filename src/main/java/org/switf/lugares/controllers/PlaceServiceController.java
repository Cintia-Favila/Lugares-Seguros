package org.switf.lugares.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switf.lugares.request.PlaceRequest;
import org.switf.lugares.response.PlaceResponse;
import org.switf.lugares.services.PlaceService;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost/", maxAge = 3600)
@RestController
@RequestMapping("/places")
public class PlaceServiceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/create")
    public ResponseEntity<PlaceResponse> createPlaceController(@RequestBody PlaceRequest placeRequest) {
        PlaceResponse createdPlace = placeService.createPlace(placeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PlaceResponse>> getAllPlacesController() {
        List<PlaceResponse> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{idPlace}")
    public ResponseEntity<PlaceResponse> getPlaceByIdController(@PathVariable Integer idPlace) {
        Optional<PlaceResponse> placeOptional = placeService.getPlaceById(idPlace);
        if (placeOptional.isPresent()) {
            return ResponseEntity.ok(placeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{idPlace}")
    public ResponseEntity<PlaceResponse> updatePlaceController(@PathVariable Integer idPlace, @RequestBody PlaceRequest place) {
        if (placeService.existsById(idPlace)) {
            PlaceResponse updatedPlace = placeService.updatePlaceByID(idPlace, place);
            return ResponseEntity.ok(updatedPlace);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{idPlace}")
    public ResponseEntity<String> deletePlaceController(@PathVariable Integer idPlace) {
        boolean deleted = placeService.deletePlaceByID(idPlace);
        if (deleted) {
            return ResponseEntity.ok("Place deleted successfully");
        } else {
            boolean placeExists = placeService.existsById(idPlace);
            if (placeExists) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete place");
            } else {
                return ResponseEntity.ofNullable("Place not found");
            }
        }
    }

    @PatchMapping("/{idPlace}/addImage")
    public ResponseEntity<PlaceResponse> savePlaceUrlImageController(@PathVariable Integer idPlace, @RequestBody PlaceRequest request) {
        if (placeService.existsById(idPlace)) {
            PlaceResponse updatedPlace = placeService.savePlaceUrlImage(idPlace, request);
            return ResponseEntity.ok(updatedPlace);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}