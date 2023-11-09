package com.app.rateme.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rateme_poi_tag")
@IdClass(PoiTagId.class)
public class PoiTag implements Serializable {
    @Id
    @Column(name = "osm_id", nullable = false)
    private Long osmId;


    @Id
    @Column(name="tag" ,nullable = false)
    private String tag;

    @Column(name= "value" ,nullable = false)
    private String value;



    public PoiTag() {
    }

    public PoiTag(Long osmId, String tag, String value) {
        this.osmId = osmId;
        this.tag = tag;
        this.value = value;
    }

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
