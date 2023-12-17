package com.app.rateme.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
import com.app.rateme.api.dto.UserResponseDto;
import com.app.rateme.model.Poi;
import com.app.rateme.model.Rating;
import com.app.rateme.model.User;
import com.app.rateme.repository.PoiDAO;
import com.app.rateme.repository.UserRepository;
import com.app.rateme.security.JwtUtil;
import com.app.rateme.services.RatingService;
import com.app.rateme.services.UserService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("rating")
//@CrossOrigin(origins = "*", allowedHeaders = {"token"}) 
public class RatingController {

    @Autowired
    private RatingService ratingService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	PoiDAO poiDAO;

	@Autowired
	JwtUtil jwtUtil;

	public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
	
	@PostMapping("/")
	public ResponseEntity<?> createRating(@RequestHeader("Authorization") String token, @RequestBody RatingDto ratingDto) {

		String jwt;
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			System.out.println("Tokennn nicht gefunden !!!!!!!!!!!!!!!!!!!!!!");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		jwt = token.split(" ")[1].trim();
		System.out.println("JWT: "  +  jwt);
		String extractedUsername = jwtUtil.extractUsername(jwt);
		System.out.println(extractedUsername);

		try{
			User user = userService.findByusername(extractedUsername);
			Rating rating = new Rating();

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
    public ResponseEntity<List<RatingDto>> getRatingsByUser(@RequestHeader("Authorization") String token) {

		 try {
			String jwt;
			if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			jwt = token.substring(7); 
			String extractedUsername = jwtUtil.extractUsername(jwt);
			final User user = userService.findByusername(extractedUsername);
			final List<RatingDto> ratingByUser = ratingService.getRatingByUser(user);
			return new ResponseEntity<>(ratingByUser, HttpStatus.OK);
		}catch(EntityNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
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
