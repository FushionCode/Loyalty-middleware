package com.vfd.open_loyalty_middleware.entity;

import com.vfd.open_loyalty_middleware.enums.ActiveStatus;
import com.vfd.open_loyalty_middleware.enums.EarningRuleType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EarningRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String description;
    private ActiveStatus active;

    @CreationTimestamp
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private EarningRuleType type;
    private String customEventName;
    private Integer points;

    public EarningRule(String test_earning_rule, String test_earning_rule1, ActiveStatus active, LocalDateTime now, LocalDateTime plusDays, EarningRuleType customEarningRule, String test_earning_rule2, int i) {
    }
}
