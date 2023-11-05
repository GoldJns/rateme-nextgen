package com.app.rateme.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.model.Rating;
import com.app.rateme.model.RatingRepository;

@RestController
@RequestMapping("ratings")
public class RatingController {

    private final RatingRepository ratingRepository;

   
    
    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


  

//test
    @GetMapping
    public List<Rating> test(){
        return List.of(
            new Rating(1,"Schmeckt gut",4,LocalDateTime.of(2023,11,4, 0, 0))
        );
    }
}
