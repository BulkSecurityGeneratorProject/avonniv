package com.avonniv.web.rest;

import com.avonniv.domain.GrantFilter;
import com.avonniv.repository.UserRepository;
import com.avonniv.security.AuthoritiesConstants;
import com.avonniv.service.GrantService;
import com.avonniv.service.MailService;
import com.avonniv.service.dto.GrantDTO;
import com.avonniv.web.rest.util.HeaderUtil;
import com.avonniv.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/grant")
public class GrantResource {

    private final Logger log = LoggerFactory.getLogger(GrantResource.class);

    private static final String ENTITY_NAME = "userManagement";

    private final UserRepository userRepository;

    private final MailService mailService;

    private final GrantService grantService;

    public GrantResource(UserRepository userRepository, MailService mailService,
                         GrantService grantService) {

        this.userRepository = userRepository;
        this.mailService = mailService;
        this.grantService = grantService;
    }

    @GetMapping("")
    @Timed
    public ResponseEntity<List<GrantDTO>> getAllGrant(@ApiParam GrantFilter grantFilter, @ApiParam Pageable pageable) {
        final Page<GrantDTO> page = grantService.getAll(grantFilter, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/grant");
        List<GrantDTO> list = page.getContent().stream().map(grantDTO -> {
            if (grantDTO.getDescription() != null) {
                String description = Jsoup.parse(grantDTO.getDescription()).text();
                grantDTO.setDescription(description);
            }
            return grantDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    @GetMapping("/filter")
    @Timed
    public ResponseEntity<List<GrantDTO>> getAllGrantFilter(@ApiParam GrantFilter grantFilter, @ApiParam Pageable pageable) {
        final Page<GrantDTO> page = grantService.getAllByFilter(grantFilter, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/grant");
        List<GrantDTO> list = page.getContent().stream().map(grantDTO -> {
            if (grantDTO.getDescription() != null) {
                String description = Jsoup.parse(grantDTO.getDescription()).text();
                grantDTO.setDescription(description);
            }
            return grantDTO;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    @Timed
    public ResponseEntity<Integer> getCount() {
        return new ResponseEntity<>(grantService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/{grantId}")
    @Timed
    public ResponseEntity<GrantDTO> getGrant(@PathVariable Long grantId) {
        log.debug("REST request to get Grant : {}", grantId);
        return ResponseUtil.wrapOrNotFound(
            grantService.getById(grantId)
                .map(GrantDTO::new));
    }

    @GetMapping("/getRecentGrants")
    @Timed
    public ResponseEntity<List<GrantDTO>> getRecentGrants() {
        return ResponseUtil.wrapOrNotFound(
            grantService.getRecentGrants()
                .map(grants -> grants.stream().map(grant -> {
                    GrantDTO grantDTO = new GrantDTO(grant);
                    if (grantDTO.getDescription() != null) {
                        String description = Jsoup.parse(grantDTO.getDescription()).text();
                        grantDTO.setDescription(description);
                    }
                    return grantDTO;
                }).collect(Collectors.toList())));
    }

    @PutMapping("")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<GrantDTO> updateGrant(@RequestBody GrantDTO grantDTO) {
        return ResponseUtil.wrapOrNotFound(grantService.update(grantDTO),
            HeaderUtil.createAlert("grantEdit.updated", grantDTO.getTitle()));
    }
}
