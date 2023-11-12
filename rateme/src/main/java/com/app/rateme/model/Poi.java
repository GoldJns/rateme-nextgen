package com.app.rateme.model;

import java.util.List;
import java.util.Set;

import com.app.rateme.model.converter.PositionConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rateme_poi")
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "osm_id")
    private long osmId;

    @Column(name = "poi_type", nullable = false)
    private String poiType;

    @Column(nullable = false)
    @Convert(converter = PositionConverter.class)
    private Position position;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "osm_id", referencedColumnName = "osm_id")
    private List<PoiTag> tags;

    @OneToMany(mappedBy = "poi")
    Set<Rating> ratings;

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
