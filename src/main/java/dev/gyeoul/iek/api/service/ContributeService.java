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

/**
 * 포털 데이터 기여 관련 서비스
 */
@Slf4j
@Service
public class ContributeService {
    // GUID 형식 검증을 위한 정규식 패턴
    private static final String GUID_REGEX = "(\\w{8})-?(\\w{4})-?(\\w{4})-?(\\w{4})-?(\\w{12})(?:\\.(\\d+))?";
    private static final Pattern GUID_PATTERN = Pattern.compile(GUID_REGEX);

    private final PortalRepository portalRepository;

    public ContributeService(PortalRepository portalRepository) {
        this.portalRepository = portalRepository;
    }

    /**
     * 포털 기여 요청 목록을 처리
     */
    public void handleContributeRequests(List<ContributePortalRequest> requests) {
        // 요청을 쿼리로 변환하고 일괄 업데이트 수행
        var queries = requests.stream()
                .map(this::generateQuery)
                .toList();
        var result = portalRepository.batchUpdate(queries);
        log.info("포털 데이터 {} 건 입력 완료", result.length);

        // 영역 내 오래된 데이터 정리
        cleanupAreaData(requests);
    }

    /**
     * 지정된 영역의 오래된 포털 데이터 정리
     */
    private void cleanupAreaData(List<ContributePortalRequest> requests) {
        // 최소 4개 이상의 유효한 요청이 있을 때만 처리
        if (requests.size() < 4) return;
        var validRequests = requests.stream()
                .filter(request -> request.getTitle() != null)
                .toList();

        try {
            // 영역의 경계값 계산
            var latMax = validRequests.stream()
                    .mapToLong(ContributePortalRequest::getLatE6)
                    .max()
                    .orElseThrow();
            var latMin = validRequests.stream()
                    .mapToLong(ContributePortalRequest::getLatE6)
                    .min()
                    .orElseThrow();
            var lngMax = validRequests.stream()
                    .mapToLong(ContributePortalRequest::getLngE6)
                    .max()
                    .orElseThrow();
            var lngMin = validRequests.stream()
                    .mapToLong(ContributePortalRequest::getLngE6)
                    .min()
                    .orElseThrow();

            // 영역 내 오래된 데이터 삭제
            var deletedCount = portalRepository.clearArea(latMin, latMax, lngMin, lngMax);
            log.info("오래된 포털 데이터 {} 건 정리 완료", deletedCount);
        } catch (Exception e) {
            log.error("데이터 정리 중 오류 발생: {}", e.getMessage());
        }
    }

    /**
     * 포털 요청을 DB 쿼리로 변환
     */
    private Query generateQuery(ContributePortalRequest request) {
        UUID guid = extractGuid(request.getGuid());
        if (guid == null) return null;
        return portalRepository.insertPortalBuilder(request, guid).setAllToExcluded();
    }

    /**
     * GUID 문자열을 파싱하여 UUID 객체로 변환
     */
    private UUID extractGuid(String guid) {
        Matcher matcher = GUID_PATTERN.matcher(guid);
        if (!matcher.matches()) return null;
        
        return UUID.fromString(String.format("%s-%s-%s-%s-%s",
                matcher.group(1),
                matcher.group(2),
                matcher.group(3),
                matcher.group(4),
                matcher.group(5))
        );
    }
}