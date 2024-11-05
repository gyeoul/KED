package dev.gyeoul.iek.api.controller;

import dev.gyeoul.iek.api.domain.response.PortalQuery;
import dev.gyeoul.iek.api.service.PortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/portals")
public class PortalController {

    private final PortalService portalService;

    public PortalController(PortalService portalService) {
        this.portalService = portalService;
    }


    @GetMapping
    public List<PortalQuery> getPortal(
            @RequestParam String query
    ) {
        return portalService.searchPortalsByName(query);

    }
}
