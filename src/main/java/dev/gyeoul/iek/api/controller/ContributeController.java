package dev.gyeoul.iek.api.controller;

import dev.gyeoul.iek.api.domain.request.ContributePortalRequest;
import dev.gyeoul.iek.api.service.ContributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class ContributeController {
    private final ContributeService contributeService;

    public ContributeController(ContributeService contributeService) {
        this.contributeService = contributeService;
    }

    @PostMapping("/push")
    @CrossOrigin(origins = "https://intel.ingress.com")
    public String pushPortalData(@RequestBody List<ContributePortalRequest> list) {
        log.info(list.toString());
        contributeService.handleContributeRequests(list);
        return "OK";
    }

}
