package com.app.rateme.model;


import com.app.rateme.model.converter.PositionConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rateme_poi")
public class Poi  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "osm_id")
    private long osmId;

    @Column(name = "poi_type", nullable = false)
    private String poiType;

    @Column(nullable = false)
    @Convert(converter = PositionConverter.class)
    private Position position;







    public Poi() {
    }

    public long getOsmId() {
        return this.osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }

    public String getPoiType() {
        return this.poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
