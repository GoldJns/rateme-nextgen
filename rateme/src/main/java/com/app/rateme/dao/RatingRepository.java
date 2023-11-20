package com.app.rateme.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rateme.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    
    public List<Rating> getRatingByUser(int user);

    public List<Rating> getRatingByPoi(long poi);

}
