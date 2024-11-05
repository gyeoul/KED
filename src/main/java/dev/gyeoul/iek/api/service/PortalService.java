package dev.gyeoul.iek.api.service;

import dev.gyeoul.iek.api.domain.response.PortalQuery;
import dev.gyeoul.iek.api.repository.PortalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PortalService {

    private final PortalRepository portalRepository;

    public PortalService(PortalRepository portalRepository) {
        this.portalRepository = portalRepository;
    }

    public List<PortalQuery> searchPortalsByName(String query) {
        return portalRepository.getPortalsByName(query).stream()
                .map(rec -> PortalQuery.builder()
                        .guid(rec.getGuid().toString())
                        .name(rec.getName())
                        .image(rec.getImage())
                        .latE6(rec.getLate6())
                        .lngE6(rec.getLnge6())
                        .lat(rec.getLate6() / 1_000_000.0)
                        .lng(rec.getLnge6() / 1_000_000.0)
                        .build())
                .toList();
    }

    public List<PortalQuery> searchSimilarPortalsByName(String query) {
        return portalRepository.getSimilarPortalsByName(query).stream()
                .map(rec -> PortalQuery.builder()
                        .guid(rec.getGuid().toString())
                        .name(rec.getName())
                        .image(rec.getImage())
                        .latE6(rec.getLate6())
                        .lngE6(rec.getLnge6())
                        .lat(rec.getLate6() / 1_000_000.0)
                        .lng(rec.getLnge6() / 1_000_000.0)
                        .build())
                .toList();

    }
}
