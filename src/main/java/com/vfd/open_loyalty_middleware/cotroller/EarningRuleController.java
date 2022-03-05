package com.vfd.open_loyalty_middleware.cotroller;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import com.vfd.open_loyalty_middleware.service.EarningRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/earningRule")
public class EarningRuleController {

    @Autowired
    private final EarningRuleService earningRuleService;

    public EarningRuleController(EarningRuleService earningRuleService) {
        this.earningRuleService = earningRuleService;
    }

    @PostMapping()
    public ResponseEntity<EarningRule> createEarningRule(@RequestBody EarningRule earningRule){
        earningRuleService.create(earningRule);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity<EarningRule> updateEarningRule(@RequestBody EarningRule earningRule, String customEventName) throws Exception {
        earningRuleService.updateEarningRule(earningRule, customEventName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public Boolean deleteEarningRule(@RequestParam String customEventName) throws Exception {
        return earningRuleService.deleteByCustomEventName(customEventName);
    }

    @GetMapping
    public List<EarningRule> findAllEarningRule() throws Exception {
        return earningRuleService.findAll();
    }

    @GetMapping("/findEarningRule")
    public EarningRule findEarningRuleByCustomEventName(@RequestBody String customEventName) throws Exception {
        return earningRuleService.findByCustomEventName(customEventName);
    }
}
