package com.vfd.open_loyalty_middleware.service.impl;

import com.vfd.open_loyalty_middleware.service.EarningRuleService;
import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EarningRuleServiceImpl implements EarningRuleService {

    @Autowired
    EarningRuleRepository earningRuleRepository;

    @Override
    public EarningRule create(EarningRule earningRule) {
        return earningRuleRepository.save(earningRule);
    }

    @Override
    public EarningRule updateEarningRule(EarningRule earningRule, String customEventName) throws Exception {
        Optional<EarningRule> earningRuleFound = earningRuleRepository.findByCustomEventName(customEventName);
        if (earningRuleFound.isPresent()){
            EarningRule earningRuleUpdate = earningRuleFound.get();

            earningRuleUpdate.setName(earningRule.getName());
            earningRuleUpdate.setDescription(earningRule.getDescription());
            earningRuleUpdate.setActive(earningRule.getActive());
            earningRuleUpdate.setType(earningRule.getType());
            earningRuleUpdate.setCustomEventName(earningRule.getCustomEventName());
            earningRuleUpdate.setPoints(earningRule.getPoints());
            earningRuleUpdate.setStartAt(earningRule.getStartAt());
            earningRuleUpdate.setEndAt(earningRule.getEndAt());

            return earningRuleRepository.save(earningRuleUpdate);
        }
        else{
            throw new Exception();
        }
    }

    @Override
    public Boolean deleteByCustomEventName(String customEventName) throws Exception {
        Optional<EarningRule> earningRuleFound = earningRuleRepository.findByCustomEventName(customEventName);
        if (earningRuleFound.isPresent()){
            EarningRule earningRule = earningRuleFound.get();
            earningRuleRepository.delete(earningRule);
            return true;
        }
        else {
            throw new Exception("EarningRule not Found");
        }

    }

    @Override
    public List<EarningRule> findAll() throws Exception {
        List<EarningRule> allEarningRule = earningRuleRepository.findAll();
        if (allEarningRule.size() == 0){
            throw new Exception("NO EARNING RULE");
        }
        return allEarningRule;
    }

    @Override
    public EarningRule findByCustomEventName(String customEventName) throws Exception {
        Optional<EarningRule> findEarningRule = earningRuleRepository.findByCustomEventName(customEventName);
        if (findEarningRule.isPresent()){
            return findEarningRule.get();
        }
        else {
            throw new Exception("NOT FOUND");
        }
    }


}
