package com.ongbl.springbatch.db.repository;

import com.ongbl.springbatch.db.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Parent SET status = :status")
    void updateStatus(@Param("status") String status);

}
