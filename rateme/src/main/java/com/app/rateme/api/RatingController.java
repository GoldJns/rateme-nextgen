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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.rateme.api.dto.RatingDto;
import com.app.rateme.dao.PoiDAO;
import com.app.rateme.dao.UserRepository;
import com.app.rateme.model.Poi;
import com.app.rateme.model.Rating;
import com.app.rateme.model.User;
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
 
	@GetMapping("/user")
	public ResponseEntity<List<RatingDto>> getRatingByUser(@RequestHeader("userId")int userId){
		try{//besser UserService benutzen anstatt Repo ->clean code
			User user = userRepository.findById(userId)
						.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));;

			final List<RatingDto> ratingByUser = ratingService.getRatingByUser(user);
			return new ResponseEntity<>(ratingByUser, HttpStatus.OK);
		}catch(EntityNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/poi/{osmId}")
	public ResponseEntity<List<RatingDto>> getRatingsByPoi(@PathVariable("osmId") long osmId) {
		try{
			Poi poi = poiDAO.findById(osmId)
						.orElseThrow(() -> new EntityNotFoundException("Poi not found with ID: " + osmId));

			final List<RatingDto> ratingsByPoi = ratingService.getRatingByPoi(poi);
			return new ResponseEntity<>(ratingsByPoi,HttpStatus.OK);
		}catch(EntityNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
