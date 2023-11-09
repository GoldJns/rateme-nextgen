package com.app.rateme.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;


public class PoiTagId implements Serializable {
    private Long osmId;
    private String tag;

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

    public PoiTagId() {
    }

    public PoiTagId(Long osmId, String tag) {
        this.osmId = osmId;
        this.tag = tag;
    }
}
