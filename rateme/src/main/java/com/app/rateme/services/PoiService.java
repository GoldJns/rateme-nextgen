package com.app.rateme.services;


import com.app.rateme.model.Poi;
import com.app.rateme.model.PoiTag;
import com.app.rateme.repository.PoiDAO;
import com.app.rateme.repository.PoiTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PoiService {
    @Autowired
    PoiDAO PoiDaoRepo;
    @Autowired
    PoiTagRepository PoitagRepo;

    public List<Poi> getAllPois() {
        final List<Poi> allPois = PoiDaoRepo.findAll();
        return allPois;
    }
    public List<PoiTag>   getTagsByOsmId( Long osmId) {
        List<PoiTag> tags = PoitagRepo.findByOsmId(osmId);
        return  tags;
    }

}
