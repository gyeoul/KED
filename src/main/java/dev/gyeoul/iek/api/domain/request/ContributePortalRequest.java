package dev.gyeoul.iek.api.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * guid  "5fd1eac6e681381e9dd9e050cb1bc175.16"
 * image "https://lh3.googleusercontent.com/5JwK5C_m8AXUovw1tuJGhWdjkgVlbuqv6yKeEYRZLZ_8SKWnPUsIqtmUqfVZ9KajP78xDV5ut2ohjbkU7rcXWapzmW9W"
 * latE6 37519813
 * lngE6 126886469
 * title "한 마리 새처럼"
 */
@Getter
@NoArgsConstructor
public class ContributePortalRequest {
    private String guid;
    private long latE6;
    private long lngE6;
    private String title;
    private String image;

    @Builder
    public ContributePortalRequest(String guid, long latE6, long lngE6, String title, String image) {
        this.guid = guid;
        this.latE6 = latE6;
        this.lngE6 = lngE6;
        this.title = title;
        this.image = image;
    }
}
