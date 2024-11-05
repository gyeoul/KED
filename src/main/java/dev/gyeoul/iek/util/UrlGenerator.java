package dev.gyeoul.iek.util;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * UrlGenerator is responsible for generating URLs based on the specified type.
 * It can generate URLs for an agent, portal, or intel map.
 */
public class UrlGenerator {

    private static final String AGENT_PATH = "agent/";
    private static final String PORTAL_PATH = "portal/";
    private static final String PLL_PARAM = "?pll=";

    public enum Type {
        AGENT,
        PORTAL,
        INTEL_MAP
    }

    @NotNull
    private final Type type;
    private final String link;
    private final Double lat;
    private final Double lng;

    private String urlWithAppParams(String specificPath) {
        return UrlConstants.APP + "?link=" + specificPath + link +
                "&apn=" + AppConstants.APN +
                "&ibi=" + AppConstants.IBI +
                "&ifl=" + AppConstants.IFL +
                "&isi=" + AppConstants.ISI;
    }

    private String generateAgent() {
        return urlWithAppParams(UrlConstants.INTEL + AGENT_PATH) +
                "&ofl=" + UrlConstants.INGRESS;
    }

    private String generatePortal() {
        return urlWithAppParams(UrlConstants.INTEL + PORTAL_PATH) +
                "&ofl=" + generateIntelMap();
    }

    private String generateIntelMap() {
        return UrlConstants.INTEL + PLL_PARAM + lat + "," + lng;
    }

    /**
     * Constructs a UrlGenerator with specified parameters.
     *
     * @param type the type of URL to generate (e.g., AGENT, PORTAL, INTEL_MAP)
     * @param link an optional link string for the URL
     * @param lat  the latitude for the portal or intel map
     * @param lng  the longitude for the portal or intel map
     */
    @Builder
    public UrlGenerator(Type type, String link, Double lat, Double lng) {
        this.type = type;
        this.link = link;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Generates a URL based on the current type of the UrlGenerator instance.
     *
     * @return the generated URL string for the specified type, or an empty string if the type is null
     */
    public String generate() {
        return switch (type) {
            case AGENT -> generateAgent();
            case PORTAL -> generatePortal();
            case INTEL_MAP -> generateIntelMap();
            case null -> throw new IllegalArgumentException("type Cannot Be Null");
        };
    }
}