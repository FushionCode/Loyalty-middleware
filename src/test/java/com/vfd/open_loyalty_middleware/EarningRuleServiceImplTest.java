package com.vfd.open_loyalty_middleware;
import com.vfd.open_loyalty_middleware.service.impl.EarningRuleServiceImpl;
import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.enums.ActiveStatus;
import com.vfd.open_loyalty_middleware.enums.EarningRuleType;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EarningRuleServiceImplTest {

    @Mock
    private EarningRuleRepository earningRuleRepository;

    @InjectMocks
    private EarningRuleServiceImpl earningRuleServiceImpl;

    @Test
    void shouldCreateEarningRule(){
        var earningRule = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.ACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(100);

        Mockito.when(earningRuleRepository.save(earningRule)).thenReturn(earningRule);
        EarningRule newEarningRule = earningRuleServiceImpl.create(earningRule);

        assertThat(newEarningRule).isNotNull();
        assertThat(newEarningRule.getCustomEventName()).isEqualTo(earningRule.getCustomEventName());
    }
}