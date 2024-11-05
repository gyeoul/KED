package dev.gyeoul.iek.api.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * guid  "5fd1eac6e681381e9dd9e050cb1bc175.16"
 * image "<a href="https://lh3.googleusercontent.com/5JwK5C_m8AXUovw1tuJGhWdjkgVlbuqv6yKeEYRZLZ_8SKWnPUsIqtmUqfVZ9KajP78xDV5ut2ohjbkU7rcXWapzmW9W">...</a>"
 * latE6 37519813
 * lngE6 126886469
 * title "한 마리 새처럼"
 */
@Getter
@NoArgsConstructor
public class ContributePortalRequest {
    private String guid; // "12d4fbbd809d4cf484959dd35636ba91.16",
    private long latE6; // 37516873,
    private long lngE6; // 126926620,
    private String title; // null,
    private String image; // null

    @Builder
    public ContributePortalRequest(String guid, long latE6, long lngE6, String title, String image) {
        this.guid = guid;
        this.latE6 = latE6;
        this.lngE6 = lngE6;
        this.title = title;
        this.image = image;
    }
}
