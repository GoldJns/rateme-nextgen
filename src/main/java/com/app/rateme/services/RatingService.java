package com.app.rateme.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.rateme.api.dto.RatingDto;
import com.app.rateme.dao.RatingRepository;
import com.app.rateme.model.Poi;
import com.app.rateme.model.Rating;
import com.app.rateme.model.User;

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
   
    public List<RatingDto> getRatingByUser(User user) {
        List<Rating> ratings = ratingRepository.getRatingByUser(user);
        return ratingToRatingDto(ratings);
    }

    public List<RatingDto> getRatingByPoi(Poi poi){
        List<Rating> ratings = ratingRepository.getRatingByPoi(poi);
        return ratingToRatingDto(ratings);
    }

    private List<RatingDto> ratingToRatingDto(List<Rating> ratings) {
        List<RatingDto> ratingDtoList = new ArrayList<>();
        for (Rating rating : ratings) {
            RatingDto ratingDto = new RatingDto();
            ratingDto.setRatingId(rating.getRatingId());
            ratingDto.setUserId(rating.getUser().getUserId()); 
            ratingDto.setOsmId(rating.getPoi().getOsmId()); 
            ratingDto.setText(rating.getText());
            ratingDto.setStars(rating.getStars());
            ratingDto.setCreatedAt(rating.getCreatedAt());
            ratingDto.setImage(rating.getImage());
            ratingDtoList.add(ratingDto);
        }
        return ratingDtoList;
    }   

}
