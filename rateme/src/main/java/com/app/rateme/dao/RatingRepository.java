package com.app.rateme.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.rateme.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
    
    public List<Rating> getRatingByUser(Rating userId);

    public List<Rating> getRatingByPoi(Rating osmId);

}
