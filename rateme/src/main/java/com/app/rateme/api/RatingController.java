package com.app.rateme.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.api.dto.RatingDto;
import com.app.rateme.dao.PoiDAO;
import com.app.rateme.dao.UserRepository;
import com.app.rateme.model.Poi;
import com.app.rateme.model.Rating;
import com.app.rateme.model.User;
import com.app.rateme.services.PoiService;
import com.app.rateme.services.RatingService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin("*")
@RequestMapping("rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PoiService poiService;

	@Autowired
	PoiDAO poiDAO;

	public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

	@PostMapping("/")
	public ResponseEntity<?> createRating(@RequestBody RatingDto ratingDto){
		try {
			Rating rating = new Rating();

			User user = userRepository.findById(ratingDto.getUserId())
					.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + ratingDto.getUserId()));;

			Poi poi = poiDAO.findById(ratingDto.getOsmId())
					.orElseThrow(() -> new EntityNotFoundException("Poi not found with ID: " + ratingDto.getOsmId()));;;

            rating.setUser(user);
            rating.setPoi(poi);
            rating.setText(ratingDto.getText());
            rating.setStars(ratingDto.getStars());
            rating.setCreatedAt(ratingDto.getCreatedAt());
            rating.setImage(ratingDto.getImage());

			Rating savedRating = ratingService.createRating(rating);
			
			return new ResponseEntity<>(savedRating, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable("userId")int userId){
		Rating rating = new Rating();

		User user = userRepository.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));;

		rating.setUser(user);
		final List<Rating> ratingByUser = ratingService.getRatingByUser(rating);
		return new ResponseEntity<>(ratingByUser, HttpStatus.OK);
	}

    @GetMapping("/poi/{osmId}")
	public ResponseEntity<List<Rating>> getRatingsByPoi(@PathVariable("osmId") long osmId) {
		Rating rating = new Rating();

		Poi poi = poiDAO.findById(osmId)
					.orElseThrow(() -> new EntityNotFoundException("Poi not found with ID: " + osmId));;;

		rating.setPoi(poi);
		final List<Rating> ratingsByPoi = ratingService.getRatingByPoi(rating);
		return new ResponseEntity<>(ratingsByPoi,HttpStatus.OK);
	}
    
	@GetMapping("/test")
	public String getTest(){
		return "Test";
	}
	//"token": "fd361438-a0ef-45ac-bf47-ebb536e5b1a0"

}
