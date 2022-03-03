package com.vfd.open_loyalty_middleware.repository;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EarningRuleRepository extends JpaRepository<EarningRule, Long> {
    Optional<EarningRule> findByCustomEventName(String customEventName);
    //List<EarningRule> findAllEarningRule();
}
