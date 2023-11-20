package com.app.rateme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rateme.dao.RatingRepository;
import com.app.rateme.model.Rating;

@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Rating rating){
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingByUser(int user){
        return ratingRepository.getRatingByUser(user);
    }

    public List<Rating> getRatingByPoi(long poi){
        return ratingRepository.getRatingByPoi(poi);
    }

}
