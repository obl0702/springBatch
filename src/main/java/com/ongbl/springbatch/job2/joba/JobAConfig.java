package com.ongbl.springbatch.job2.joba;

import com.ongbl.springbatch.db.entity.Parent;
import com.ongbl.springbatch.job.ParentProcessor;
import com.ongbl.springbatch.job2.config.JobCommonListenerV2;
import com.ongbl.springbatch.job2.config.StockService;
import com.ongbl.springbatch.job2.config.TotalCountStrategy;
import com.ongbl.springbatch.job2.config.TotalCountStrategyFactory;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobAConfig {
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory emf;
    private final JobRepository jobRepository;
    private final ParentProcessor parentProcessor;
    private final TotalCountStrategyFactory totalCountStrategyFactory;
    private final StockService stockService;

    @Bean
    @Qualifier("aaaJob")
    public Job aaaJob(Step step) {
        return new JobBuilder
                ("JobA", jobRepository)
                .listener(new JobCommonListenerV2(totalCountStrategyFactory, stockService))
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step aaaStep() {
        return new StepBuilder("stepA", jobRepository)
                .<Parent, Parent>chunk(5, transactionManager)
                .reader(aaaReader())
                .processor(parentProcessor)
                .writer(aaaWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Parent> aaaReader() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "P001");
        JpaPagingItemReader<Parent> reader = new JpaPagingItemReader<>();
        reader.setQueryString("SELECT p FROM Parent p where p.name = :name");
        reader.setParameterValues(paramMap);
        reader.setPageSize(2);
        reader.setEntityManagerFactory(emf);
        return reader;
    }

    @Bean
    public JpaItemWriter<Parent> aaaWriter() {
        return new JpaItemWriterBuilder<Parent>()
                .entityManagerFactory(emf)
                .build();
    }
}
