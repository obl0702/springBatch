package com.ongbl.springbatch.job2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobCommonListenerV2 implements JobExecutionListener {
    private final TotalCountStrategyFactory totalCountStrategyFactory;
    private final StockService stockService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        TotalCountStrategy totalCountStrategy = totalCountStrategyFactory.getTotalCountStrategy(jobName);

        long totalCount = totalCountStrategy.getTotalCount();
        if(totalCount > 0) {
            // Assuming you have appropriate parameters to pass to send method of StockService
            stockService.send(); // Pass necessary parameters here
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        stockService.send();
    }
}
