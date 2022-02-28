package service.serviceImpl;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EarningRuleService;

@Service
public class EarningRuleServiceImpl implements EarningRuleService {

    @Autowired
    EarningRuleRepository earningRuleRepository;

    @Override
    public EarningRule createRule(EarningRule earningRule) {
        return earningRuleRepository.save(earningRule);
    }
}
