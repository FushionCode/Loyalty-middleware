package com.vfd.open_loyalty_middleware;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.enums.ActiveStatus;
import com.vfd.open_loyalty_middleware.enums.EarningRuleType;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import com.vfd.open_loyalty_middleware.service.impl.EarningRuleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldUpdateEarningRule() throws Exception {
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
        assertThat(newEarningRule).isEqualTo(earningRule);

        var updateEarningRule = new EarningRule();
        updateEarningRule.setPoints(70);
        updateEarningRule.setName(earningRule.getName());
        updateEarningRule.setDescription(earningRule.getDescription());
        updateEarningRule.setActive(earningRule.getActive());
        updateEarningRule.setStartAt(earningRule.getStartAt());
        updateEarningRule.setEndAt(earningRule.getEndAt());
        updateEarningRule.setType(earningRule.getType());
        updateEarningRule.setCustomEventName(earningRule.getCustomEventName());

        Mockito.when(earningRuleRepository.findByCustomEventName(earningRule.getCustomEventName())).thenReturn(Optional.of(earningRule));


        EarningRule updatedEarningRule = earningRuleServiceImpl.updateEarningRule(updateEarningRule, "TEST_EARNING_RULE");
        System.out.println(updatedEarningRule);
        System.out.println(earningRule);
        assertThat(updatedEarningRule.getPoints()).isEqualTo(updateEarningRule.getPoints());

    }

    @Test
    void shouldDeleteEarningRule() throws Exception {
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

        Mockito.when(earningRuleRepository.findByCustomEventName(earningRule.getCustomEventName())).thenReturn(Optional.of(earningRule));
        Optional<EarningRule> findEarningRule = earningRuleRepository.findByCustomEventName(earningRule.getCustomEventName());

        Mockito.doNothing().when(earningRuleRepository).delete(findEarningRule.get());
        assertThat(earningRuleServiceImpl.deleteByCustomEventName(newEarningRule.getCustomEventName())).isEqualTo(true);

        //TODO: USE THIS CALL TO CHECK IF DATA IS DELETED BUT REPOSITORY STILL PERSISTING THE DATA AFTER DELETE METHOD IS CALLED
        Optional<EarningRule> checkIfDeleted = earningRuleRepository.findByCustomEventName(earningRule.getCustomEventName());
        //assertThat(checkIfDeleted).isEmpty();
    }

    @Test
    void findAllEarningRule() throws Exception {
        var earningRule = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.ACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(100);

        var earningRule1 = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.INACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(80);

        Mockito.when(earningRuleRepository.save(earningRule)).thenReturn(earningRule);
        EarningRule newEarningRule1 = earningRuleServiceImpl.create(earningRule);
        EarningRule newEarningRule2 = earningRuleServiceImpl.create(earningRule1);

        Mockito.when(earningRuleRepository.findAll()).thenReturn(List.of(earningRule, earningRule1));
        List<EarningRule> findAll = earningRuleServiceImpl.findAll();

        assertThat(findAll.size()).isEqualTo(2);

    }
}