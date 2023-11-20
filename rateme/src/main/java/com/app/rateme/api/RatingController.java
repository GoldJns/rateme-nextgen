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
import com.app.rateme.model.Rating;
import com.app.rateme.services.RatingService;
import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

	public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

	@PostMapping("/createRating")
	public ResponseEntity<?> createRating(@RequestBody RatingDto ratingDto){
		try {
			Rating rating = new Rating();
            rating.setUserId(ratingDto.getUserId());
            rating.setosmId(ratingDto.getOsmId());
            rating.setText(ratingDto.getText());
            rating.setStars(ratingDto.getStars());
            rating.setCreatedAt(ratingDto.getCreatedAt());
            rating.setImage(ratingDto.getImage());

			Rating savedRating = ratingService.createRating(rating);
			
			return new ResponseEntity<>(savedRating, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable("userId")int userId){
		Rating rating = new Rating();
		rating.setUserId(userId);
		final List<Rating> ratingByUser = ratingService.getRatingByUser(rating);
		return new ResponseEntity<>(ratingByUser, HttpStatus.OK);
	}

    @GetMapping("/poi/{osmId}")
	public ResponseEntity<List<Rating>> getRatingsByPoi(@PathVariable("osmId") long osmId) {
		Rating rating = new Rating();
		rating.setosmId(osmId);
		final List<Rating> ratingsByPoi = ratingService.getRatingByPoi(rating);
		return new ResponseEntity<>(ratingsByPoi,HttpStatus.OK);
	}
    
}
