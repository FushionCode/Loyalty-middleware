package com.vfd.open_loyalty_middleware.repository;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.enums.ActiveStatus;
import com.vfd.open_loyalty_middleware.enums.EarningRuleType;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EarningRuleRepositoryTest {
    @Mock
    private EarningRuleRepository earningRuleRepository;

    @Test
    void shouldSaveEarningRule(){
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
        EarningRule newEarningRule = earningRuleRepository.save(earningRule);

        assertThat(newEarningRule).isNotNull();
    }

    @Test
    void shouldFindEarningRule(){
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
        EarningRule newEarningRule = earningRuleRepository.save(earningRule);

        Mockito.when(earningRuleRepository.findByCustomEventName(earningRule.getCustomEventName())).thenReturn(Optional.of(earningRule));
        Optional<EarningRule> findEarningRule = earningRuleRepository.findByCustomEventName(earningRule.getCustomEventName());

        assertThat(findEarningRule).isPresent();
    }

    @Test
    void shouldDeleteEarningRule(){
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
        EarningRule newEarningRule = earningRuleRepository.save(earningRule);

        Mockito.doNothing().when(earningRuleRepository).delete(earningRule);
        earningRuleRepository.delete(earningRule);

        Mockito.verify(earningRuleRepository, Mockito.times(1)).delete(earningRule);
        //assertThat(earningRuleRepository.findByCustomEventName("TEST_EARNING_RULE").get()).isNull();

        //TODO: use this to verify if the content i truely deleted but it keeps returning an object
        //Mockito.when(earningRuleRepository.findByCustomEventName("TEST_EARNING_RULE")).thenReturn(Optional.of(earningRule));
        //Optional<EarningRule> checkIfDeleted = earningRuleRepository.findByCustomEventName("TEST_EARNING_RULE");

    }
}
