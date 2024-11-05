package dev.gyeoul.iek.api.service;

import dev.gyeoul.iek.api.domain.request.ContributePortalRequest;
import dev.gyeoul.iek.api.repository.PortalRepository;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ContributeService {
    private static final String GUID_REGEX = "(\\w{8})-?(\\w{4})-?(\\w{4})-?(\\w{4})-?(\\w{12})(?:\\.(\\d+))?";
    private static final Pattern GUID_PATTERN = Pattern.compile(GUID_REGEX);

    private final PortalRepository portalRepository;

    public ContributeService(PortalRepository portalRepository) {
        this.portalRepository = portalRepository;
    }

    public void handleContributeRequests(List<ContributePortalRequest> requests) {
        var queries = requests.stream()
                .map(this::generateQuery)
                .toList();
        var result = portalRepository.batchUpdate(queries);
        log.info("Inserted {} records", result.length);
    }

    private Query generateQuery(ContributePortalRequest request) {
        UUID guid = extractGuid(request.getGuid());
        if (guid == null) return null;
        return portalRepository.insertPortalBuilder(request, guid).setAllToExcluded();
    }

    private UUID extractGuid(String guid) {
        Matcher matcher = GUID_PATTERN.matcher(guid);
        if (!matcher.matches()) return null;
        return UUID.fromString(
                String.format("%s-%s-%s-%s-%s",
                        matcher.group(1),
                        matcher.group(2),
                        matcher.group(3),
                        matcher.group(4),
                        matcher.group(5))
        );
    }

}