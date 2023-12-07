package com.app.rateme.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.rateme.model.Poi;
import com.app.rateme.model.Rating;
import com.app.rateme.model.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
    
    public List<Rating> getRatingByUser(User user);

    public List<Rating> getRatingByPoi(Poi poi);

}
