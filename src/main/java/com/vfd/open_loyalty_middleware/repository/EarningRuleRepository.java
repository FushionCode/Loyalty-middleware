package com.vfd.open_loyalty_middleware.repository;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarningRuleRepository extends JpaRepository<EarningRule, Long> {
    
}
