package com.app.rateme.api.dto;

import com.app.rateme.model.Poi;
import com.app.rateme.model.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class PoiResponseData  implements Serializable {



        private long osmId;
        private String poiType;
        private Position position;

        public long getOsmId() {
            return osmId;
        }

        public void setOsmId(long osmId) {
            this.osmId = osmId;
        }

        public String getPoiType() {
            return poiType;
        }

        public void setPoiType(String poiType) {
            this.poiType = poiType;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }



        public static PoiResponseData fromEntity(Poi entity) {
            PoiResponseData poi = new PoiResponseData();
            poi.setOsmId(entity.getOsmId());
            poi.setPoiType(entity.getPoiType());
            poi.setPosition(entity.getPosition());
            return poi;
        }
    }


