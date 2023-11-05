package com.app.rateme.api;


import com.app.rateme.api.dto.PoiResponseData;
import com.app.rateme.dao.PoiDAO;
import com.app.rateme.model.Poi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/pois")
public class PoiController {


    @Autowired
    PoiDAO PoiDaorepo;
    @GetMapping
    public ResponseEntity getAllPois() {
        final List<Poi> allPois = PoiDaorepo.findAll();

        final List<PoiResponseData> poiData = allPois.parallelStream().map(PoiResponseData::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(poiData);
    }
}
