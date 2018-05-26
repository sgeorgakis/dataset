package com.syntax.interview.service.impl;

import com.syntax.interview.domain.ChangeSet;
import com.syntax.interview.repository.ChangeSetRepository;
import com.syntax.interview.service.ChangeSetService;
import com.syntax.interview.service.DiffReportService;
import com.syntax.interview.service.dto.changeset.ChangeSetDTO;
import com.syntax.interview.service.dto.report.Diff;
import com.syntax.interview.service.mapper.ChangeSetMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Optional;

@Service
public class ChangeSetServiceImpl implements ChangeSetService {

    private final ChangeSetRepository changeSetRepository;

    private final ChangeSetMapper mapper;

    private final DiffReportService diffReportService;

    private final ResourceLoader resourceLoader;

    public ChangeSetServiceImpl(ChangeSetRepository changeSetRepository, ChangeSetMapper mapper,
                                DiffReportService diffReportService, ResourceLoader resourceLoader) {
        this.changeSetRepository = changeSetRepository;
        this.mapper = mapper;
        this.diffReportService = diffReportService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Diff getDiffReport(Long previousId, Long currentId) {
        Optional<ChangeSetDTO> previous = changeSetRepository.findOneById(previousId).map(mapper::toDto);
        Optional<ChangeSetDTO> current = changeSetRepository.findOneById(currentId).map(mapper::toDto);

        if (!previous.isPresent()) {
            throw new RuntimeException("First changeSet data not present");
        }

        if (!current.isPresent()) {
            throw new RuntimeException("Second changeSet data not present");
        }

        return diffReportService.calculateReport(previous.get().getData(), current.get().getData());
    }

    @PostConstruct
    private void loadData() throws IOException  {

        InputStream firstDataStream = resourceLoader.getResource("classpath:DemoData.json").getInputStream();
        StringWriter firstWriter = new StringWriter();
        IOUtils.copy(firstDataStream, firstWriter);
        String firstData = firstWriter.toString();
        Optional<ChangeSet> changeSet = changeSetRepository.findOneById(1L);
        changeSet.ifPresent(c -> {
            c.setData(firstData);
            changeSetRepository.save(c);
        });
        firstDataStream.close();
        firstWriter.close();

        InputStream secondDataStream = resourceLoader.getResource("classpath:DemoData_V2.json").getInputStream();
        StringWriter secondWriter = new StringWriter();
        IOUtils.copy(secondDataStream, secondWriter);
        String secondData = secondWriter.toString();
        Optional<ChangeSet> secondChangeSet = changeSetRepository.findOneById(2L);
        secondChangeSet.ifPresent(c -> {
            c.setData(secondData);
            changeSetRepository.save(c);
        });
        secondDataStream.close();
        secondWriter.close();
    }
}
