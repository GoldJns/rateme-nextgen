package com.app.rateme.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rateme.model.PoiTag;


public interface PoiTagRepository extends JpaRepository<PoiTag, Long> {
    List<PoiTag> findByOsmId(Long osmId);

}
