package com.vfd.open_loyalty_middleware.cotroller;

import com.vfd.open_loyalty_middleware.entity.EarningRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.EarningRuleService;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/earningRule")
public class EarningRuleController {

    @Autowired
    private EarningRuleService earningRuleService;


    @PostMapping()
    public ResponseEntity<EarningRule> createEarningRule(@RequestBody EarningRule earningRule){
        earningRuleService.createRule(earningRule);
        return new ResponseEntity<EarningRule>(HttpStatus.OK);
    }
}
