package com.wanted.preonboarding.global.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.preonboarding.WantedPreOnboardingBackendApplication;
import jakarta.transaction.Transactional;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WantedPreOnboardingBackendApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@Ignore
public abstract class IntegrationTest {
    @Autowired protected MockMvc mvc;
    @Autowired protected ObjectMapper objectMapper;
}