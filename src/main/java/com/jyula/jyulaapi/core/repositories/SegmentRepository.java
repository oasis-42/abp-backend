package com.jyula.jyulaapi.core.repositories;

import com.jyula.jyulaapi.core.enterprise.CustomQuerydslPredicateExecutor;
import com.jyula.jyulaapi.core.entities.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long>, CustomQuerydslPredicateExecutor<Segment> {
}
