package com.ongbl.springbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobCommonListener implements JobExecutionListener {

    private final CountProvider countProvider;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        long totalCount = countProvider.getTotalCount();
        log.info("totalCount: " + totalCount);

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
