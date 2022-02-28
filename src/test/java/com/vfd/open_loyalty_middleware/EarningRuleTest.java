package com.vfd.open_loyalty_middleware;

import com.vfd.open_loyalty_middleware.cotroller.EarningRuleController;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import service.EarningRuleService;
import service.serviceImpl.EarningRuleServiceImpl;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EarningRuleTest {

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
        EarningRule newEarningRule = earningRuleServiceImpl.createRule(earningRule);

        assertThat(newEarningRule).isNotNull();
        assertThat(newEarningRule.getCustomEventName()).isEqualTo(earningRule.getCustomEventName());
    }

//    @Autowired
//    private TestRestTemplate testRestTemplate;
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EarningRuleService earningRuleServ;

    @InjectMocks
    private EarningRuleController earningRuleController;

    @Test
    void shouldGetHttpStatusCodeOK() throws Exception {
        EarningRule earningRule = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.ACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(100);

        /*  This test is failing when I use the TestRestTemplate library
            The errors I get are: NullPointerExceptions when I auto wire the TestRestTemplate library, but when
            instantiate a Object of the TestRestTemplate it shows an illegalArgumentException:URI is not absolute
         */

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<EarningRule> request = new HttpEntity<>(earningRule, headers);
//        ResponseEntity<Void> response = this.testRestTemplate.postForEntity("/earningRule", request, Void.class);
//        assertEquals(200, response.getStatusCodeValue());


        //This is working
        ResponseEntity<EarningRule> response = earningRuleController.createEarningRule(earningRule);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /* This is also throwing a nullPointerException for the MockMvc */

//        Mockito.when(earningRuleServ.createRule(earningRule)).thenReturn(earningRule);
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post("/earningRule"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}