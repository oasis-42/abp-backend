package com.jyula.jyulaapi.core.repositories;

import com.jyula.jyulaapi.core.enterprise.CustomQuerydslPredicateExecutor;
import com.jyula.jyulaapi.core.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>, CustomQuerydslPredicateExecutor<Campaign> {
}
