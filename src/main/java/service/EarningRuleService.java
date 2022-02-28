package service;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import org.springframework.stereotype.Service;

@Service
public interface EarningRuleService {
    EarningRule createRule(EarningRule earningRule);
}
