package com.celonis.challenge.test.medium;

import com.celonis.challenge.ChallengeApplication;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChallengeApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class AbstractMediumTests {

    public final Logger LOG = LoggerFactory.getLogger(AbstractMediumTests.class);
    public final String HEADER_NAME = "Celonis-Auth";
    public final String HEADER_VALUE = "totally_secret";

    @Before
    public void setupClass() {
        LOG.info("executing integration tests");
    }
}
