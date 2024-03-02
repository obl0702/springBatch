package com.ongbl.springbatch.job;

import com.ongbl.springbatch.DefaultBatchConfig;
import com.ongbl.springbatch.TestBatchConfig;
import com.ongbl.springbatch.db.repository.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TestBatchConfig.class, ParentJobConfig.class, ParentProcessor.class, ParentRepository.class, ParentScheduler.class})
@SpringBatchTest
@Import(DefaultBatchConfig.class)
class ParentJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Qualifier("parentJob")
    @Autowired
    private Job job;

    @Autowired
    private ParentRepository parentRepository;

    @BeforeEach
    void setup(){
        parentRepository.updateStatus("PENDING");
    }

    @Test
    void testParentJob() throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(params);

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    }



}