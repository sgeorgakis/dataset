package com.syntax.interview.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntax.interview.WebApp;
import com.syntax.interview.domain.ChangeSet;
import com.syntax.interview.repository.ChangeSetRepository;
import com.syntax.interview.service.ChangeSetService;
import com.syntax.interview.service.dto.report.Diff;
import com.syntax.interview.service.mapper.ChangeSetMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApp.class)
public class ChangeSetControllerTest {

    @Autowired
    private ChangeSetRepository changeSetRepository;

    @Autowired
    private ChangeSetService changeSetService;

    @Autowired
    private ChangeSetMapper changeSetMapper;

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private ResourceLoader resourceLoader;

    private MockMvc restMvc;


    @Before
    public void setup() {

        ChangeSetController changeSetController = new ChangeSetController(changeSetMapper, changeSetService);

        this.restMvc = MockMvcBuilders.standaloneSetup(changeSetController)
                .setMessageConverters(httpMessageConverters)
                .build();
    }

    @Test
    @Ignore
    public void calculateDiffTest() throws Exception {

        Optional<ChangeSet> changeSet = changeSetRepository.findOneById(1L);
        Optional<ChangeSet> anotherChangeSet = changeSetRepository.findOneById(2L);

        assertThat(changeSet).isPresent();
        assertThat(anotherChangeSet).isPresent();
        assertThat(changeSet.get().getData()).isNotNull();
        assertThat(anotherChangeSet.get().getData()).isNotNull();

        InputStream diffData = resourceLoader.getResource("classpath:DemoDataDiff.json").getInputStream();
        assertThat(diffData).isNotNull();

        ObjectMapper mapper = new ObjectMapper();
        Diff expectedDiff = mapper.readValue(diffData, Diff.class);
        assertThat(expectedDiff).isNotNull();

        String response = restMvc.perform(get("/api/change-set/" + 1 +"/" + 2))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Diff resultDiff = mapper.readValue(response, Diff.class);
        assertThat(resultDiff).isNotNull();
        assertThat(resultDiff).isEqualTo(expectedDiff);
    }
}