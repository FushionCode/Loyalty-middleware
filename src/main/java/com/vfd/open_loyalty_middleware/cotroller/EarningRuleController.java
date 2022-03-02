package com.vfd.open_loyalty_middleware.cotroller;

import com.vfd.open_loyalty_middleware.service.EarningRuleService;
import com.vfd.open_loyalty_middleware.entity.EarningRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
