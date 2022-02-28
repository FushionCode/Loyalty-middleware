package com.vfd.open_loyalty_middleware.service.impl;

import com.vfd.open_loyalty_middleware.service.EarningRuleService;
import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarningRuleServiceImpl implements EarningRuleService {

    @Autowired
    EarningRuleRepository earningRuleRepository;

    @Override
    public EarningRule create(EarningRule earningRule) {
        return earningRuleRepository.save(earningRule);
    }
}
