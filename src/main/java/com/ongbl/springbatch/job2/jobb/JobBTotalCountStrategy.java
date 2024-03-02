package com.ongbl.springbatch.job2.jobb;

import com.ongbl.springbatch.job2.config.TotalCountStrategy;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JobBTotalCountStrategy implements TotalCountStrategy {

    private final EntityManagerFactory emf;

    @Override
    public long getTotalCount() {
        TypedQuery<Long> countQuery = emf.createEntityManager()
                .createQuery("SELECT p FROM Parent p where p.name = :name", Long.class)
                .setParameter("name", "P002");
        return countQuery.getSingleResult();
    }
}
