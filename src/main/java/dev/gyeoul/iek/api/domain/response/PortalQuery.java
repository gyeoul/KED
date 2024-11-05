package dev.gyeoul.iek.api.domain.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PortalQuery {
    private final String guid;
    private final String name;
    private final String image;
    private final String address;
    private final long latE6;
    private final long lngE6;
    private final double lat;
    private final double lng;

    @Builder
    public PortalQuery(String guid, String name, String image, String address, long latE6, long lngE6, double lat, double lng) {
        this.guid = guid;
        this.name = name;
        this.image = image;
        this.address = address;
        this.latE6 = latE6;
        this.lngE6 = lngE6;
        this.lat = lat;
        this.lng = lng;
    }

}
