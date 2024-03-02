package com.ongbl.springbatch.job2.joba;

import com.ongbl.springbatch.job2.config.TotalCountStrategy;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JobATotalCountStrategy implements TotalCountStrategy {

    private final EntityManagerFactory emf;

    @Override
    public long getTotalCount() {
        TypedQuery<Long> countQuery = emf.createEntityManager()
                .createQuery("SELECT p FROM Parent p where p.name = :name", Long.class)
                .setParameter("name", "P001");
        return countQuery.getSingleResult();
    }
}
