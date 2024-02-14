package com.ongbl.springbatch.job;

import com.ongbl.springbatch.db.entity.Parent;
import jakarta.persistence.EntityManagerFactory;
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
public class ParentJobConfig {
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory emf;
    private final JobRepository jobRepository;
    private final ParentProcessor parentProcessor;

    @Bean
    @Qualifier("parentJob")
    public Job parentJob(Step step) {
        return new JobBuilder
                ("job_name", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step() {
        return new StepBuilder("step_name", jobRepository)
                .<Parent, Parent>chunk(5, transactionManager)
                .reader(reader())
                .processor(parentProcessor)
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Parent> reader() {
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
    public JpaItemWriter<Parent> writer() {
        return new JpaItemWriterBuilder<Parent>()
                .entityManagerFactory(emf)
                .build();
    }
}
