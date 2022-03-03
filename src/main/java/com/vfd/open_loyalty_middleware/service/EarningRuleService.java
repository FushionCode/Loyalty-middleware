package com.vfd.open_loyalty_middleware.service;

import com.vfd.open_loyalty_middleware.entity.EarningRule;

import java.util.List;

public interface EarningRuleService {
    EarningRule create(EarningRule earningRule);
    EarningRule updateEarningRule(EarningRule earningRule, String customEventName) throws Exception;
    Boolean deleteByCustomEventName(String customEventName) throws Exception;
    List<EarningRule> findAll() throws Exception;
    EarningRule findByCustomEventName(String customEventName) throws Exception;
}
