package com.app.rateme.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.dao.PoiDAO;
import com.app.rateme.dao.RatingRepository;
import com.app.rateme.model.Poi;
import com.app.rateme.model.Rating;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("ratings")
public class RatingController {


    @Autowired
    private RatingRepository ratingRepository;

	@Autowired
    private PoiDAO poiRepository;

   
    


  
//Test ???
    @GetMapping("/poi/{osmId}")
	public ResponseEntity<List<Rating>> getRatingsByPoi(@PathParam("osmId") long osmId) {
		Optional<Poi> poi = poiRepository.findById(osmId);
		final List<Rating> ratingsByPoi = ratingRepository.findAllByPoi(poi);

		return new ResponseEntity<>(ratingsByPoi,HttpStatus.OK);
	}
    
}
