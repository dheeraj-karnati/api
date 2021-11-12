package com.ekinsol.challenge.apiservice.bo;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class CostItem {
    private String roleName;
    private BigDecimal year1Amount;
    private BigDecimal year2Amount;
    private BigDecimal year3Amount;
    private BigDecimal year4Amount;
    private BigDecimal year5Amount;
    private BigDecimal totalAmount;
}
