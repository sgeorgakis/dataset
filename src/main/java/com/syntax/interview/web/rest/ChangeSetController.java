package com.syntax.interview.web.rest;

import com.syntax.interview.service.ChangeSetService;
import com.syntax.interview.service.dto.report.Diff;
import com.syntax.interview.service.mapper.ChangeSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChangeSetController {

    private final ChangeSetMapper mapper;

    private final ChangeSetService changeSetService;

    private final Logger log = LoggerFactory.getLogger(ChangeSetController.class);

    public ChangeSetController(ChangeSetMapper mapper, ChangeSetService changeSetService) {
        this.mapper = mapper;
        this.changeSetService = changeSetService;
    }

    @GetMapping(path = "/change-set/{previous}/{next}")
    public ResponseEntity<Diff> calculateDiffReport(@PathVariable("previous") Integer previous, @PathVariable("next") Integer next) {
        return ResponseEntity.ok(changeSetService.getDiffReport(previous.longValue(), next.longValue()));
    }
}
