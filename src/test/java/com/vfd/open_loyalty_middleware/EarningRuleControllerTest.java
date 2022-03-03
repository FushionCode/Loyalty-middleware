package com.vfd.open_loyalty_middleware;

import com.vfd.open_loyalty_middleware.cotroller.EarningRuleController;
import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.enums.ActiveStatus;
import com.vfd.open_loyalty_middleware.enums.EarningRuleType;
import com.vfd.open_loyalty_middleware.repository.EarningRuleRepository;
import com.vfd.open_loyalty_middleware.service.EarningRuleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EarningRuleControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    //TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EarningRuleService earningRuleService;

    @Mock
    private EarningRuleRepository earningRuleRepository;

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

    @Test
    void shouldCreateRuleFromController() {

        EarningRule earningRule = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.ACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(100);

        Mockito.when(earningRuleService.create(earningRule)).thenReturn(earningRule);
        ResponseEntity<EarningRule> response = earningRuleController.createEarningRule(earningRule);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(newEarningRule.getCustomEventName()).isEqualTo(earningRule.getCustomEventName());
    }

    @Test
    void shouldUpdateEarningRule() throws Exception {
        EarningRule earningRule = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.ACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(100);

        Mockito.when(earningRuleService.create(earningRule)).thenReturn(earningRule);
        ResponseEntity<EarningRule> response = earningRuleController.createEarningRule(earningRule);
        System.out.println(response);

        var updateEarningRule = new EarningRule();
        updateEarningRule.setPoints(70);
        updateEarningRule.setName(earningRule.getName());
        updateEarningRule.setDescription(earningRule.getDescription());
        updateEarningRule.setActive(earningRule.getActive());
        updateEarningRule.setStartAt(earningRule.getStartAt());
        updateEarningRule.setEndAt(earningRule.getEndAt());
        updateEarningRule.setType(earningRule.getType());
        updateEarningRule.setCustomEventName(earningRule.getCustomEventName());

        Mockito.when(earningRuleService.updateEarningRule(updateEarningRule, earningRule.getCustomEventName()))
                .thenReturn(earningRule);
        ResponseEntity<EarningRule> updateResponse
                = earningRuleController.updateEarningRule(updateEarningRule, "TEST_EARNING_RULE");
        System.out.println(updateResponse);

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldDeleteEarningRule() throws Exception {
        EarningRule earningRule = new EarningRule();
        earningRule.setName("Test earning rule");
        earningRule.setDescription("Test earning rule");
        earningRule.setActive(ActiveStatus.ACTIVE);
        earningRule.setStartAt(LocalDateTime.now());
        earningRule.setEndAt(earningRule.getStartAt().plusDays(30L));
        earningRule.setType(EarningRuleType.CUSTOM_EARNING_RULE);
        earningRule.setCustomEventName("TEST_EARNING_RULE");
        earningRule.setPoints(100);

        Mockito.when(earningRuleService.create(earningRule)).thenReturn(earningRule);
        ResponseEntity<EarningRule> response = earningRuleController.createEarningRule(earningRule);

        Mockito.when(earningRuleService.deleteByCustomEventName("TEST_EARNING_RULE")).thenReturn(true);
        assertThat(earningRuleController.deleteEarningRule("TEST_EARNING_RULE")).isEqualTo(true);
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

        Mockito.when(earningRuleService.create(earningRule)).thenReturn(earningRule);
        ResponseEntity<EarningRule> newEarningRule1 = earningRuleController.createEarningRule(earningRule);
        ResponseEntity<EarningRule> newEarningRule2 = earningRuleController.createEarningRule(earningRule1);

        Mockito.when(earningRuleService.findAll()).thenReturn(List.of(earningRule, earningRule1));
        List<EarningRule> findAll = earningRuleController.findAllEarningRule();

        assertThat(findAll.size()).isEqualTo(2);
    }
}