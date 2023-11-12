package com.app.rateme.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rateme.model.Poi;

public interface PoiDAO extends JpaRepository<Poi, Long> {

}
