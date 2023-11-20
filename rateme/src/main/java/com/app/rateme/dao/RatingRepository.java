package com.app.rateme.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rateme.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    
    /*public List<Rating> findAllByPoi(Optional<Poi> poi);*/
}
