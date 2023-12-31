package com.app.rateme.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rateme.model.Poi;
import com.app.rateme.model.PoiTag;
import com.app.rateme.services.PoiService;

@RestController
@RequestMapping("/pois")
public class PoiController {

    @Autowired
    PoiService poiService;

    @GetMapping
    public ResponseEntity<List<Poi>> getAllPois() {
        final List<Poi> allPois = poiService.getAllPois();
        return ResponseEntity.ok(allPois);
    }

    @GetMapping("/{osmId}")
    public ResponseEntity<List<PoiTag>> getTagsByOsmId(@PathVariable Long osmId) {
        List<PoiTag> tags = poiService.getTagsByOsmId(osmId);

        return ResponseEntity.ok(tags); // Return the found tags

    }
}
