package com.ongbl.springbatch.db.repository;

import com.ongbl.springbatch.db.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
