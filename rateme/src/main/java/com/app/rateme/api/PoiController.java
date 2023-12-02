package com.app.rateme.api;

import com.app.rateme.model.Poi;
import com.app.rateme.model.PoiTag;
import com.app.rateme.services.PoiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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
