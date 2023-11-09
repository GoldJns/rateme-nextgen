package com.app.rateme.dao;

import com.app.rateme.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoiDAO extends JpaRepository<Poi, Long> {

}
