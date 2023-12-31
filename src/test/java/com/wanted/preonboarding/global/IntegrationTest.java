package com.wanted.preonboarding.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.preonboarding.RestApiMaturityThreeLevelApplication;
import jakarta.transaction.Transactional;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiMaturityThreeLevelApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@Ignore
public abstract class IntegrationTest {
    @Autowired protected MockMvc mvc;
    @Autowired protected ObjectMapper objectMapper;
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
}