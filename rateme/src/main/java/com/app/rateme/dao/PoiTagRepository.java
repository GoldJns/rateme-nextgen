package com.app.rateme.dao;

import com.app.rateme.model.PoiTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PoiTagRepository extends JpaRepository<PoiTag, Long> {
    List<PoiTag> findByOsmId(Long osmId);

}
