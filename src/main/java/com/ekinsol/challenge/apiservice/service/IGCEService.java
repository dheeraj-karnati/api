package com.ekinsol.challenge.apiservice.service;

import com.ekinsol.challenge.apiservice.bo.CostItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IGCEService {

    private static BigDecimal NO_OF_HOURS = BigDecimal.valueOf(1820);
    private static BigDecimal INFLATION_PRICE = BigDecimal.valueOf(1.04);
    private static Map<String, BigDecimal> ROLE_PRICE_MAP = new HashMap<>();
    static {
        ROLE_PRICE_MAP.put("Project Manager (Senior)", BigDecimal.valueOf(142.08));
        ROLE_PRICE_MAP.put("Scrum Master (senior)", BigDecimal.valueOf(115.04));
        ROLE_PRICE_MAP.put("Solutions Architect (senior)", BigDecimal.valueOf(213.60));
        ROLE_PRICE_MAP.put("Lead Developer (Senior)", BigDecimal.valueOf(127.44));
        ROLE_PRICE_MAP.put("Developer (Mid-level)", BigDecimal.valueOf(99.43));
        ROLE_PRICE_MAP.put("Product Designer (senior)", BigDecimal.valueOf(152.45));
    }

    public List<CostItem> getCostEstimation() {
        List<CostItem> costItems = ROLE_PRICE_MAP.entrySet().stream().map(entry -> createCostItem(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return costItems;
    }

    private CostItem createCostItem(String role, BigDecimal hourlyRate) {
        BigDecimal yearlyPrice = hourlyRate.multiply(NO_OF_HOURS).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal yearly2Price = yearlyPrice.multiply(INFLATION_PRICE).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal yearly3Price = yearly2Price.multiply(INFLATION_PRICE).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal yearly4Price = yearly3Price.multiply(INFLATION_PRICE).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal yearly5Price = yearly4Price.multiply(INFLATION_PRICE).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal totalPrice = yearlyPrice.add(yearly2Price).add(yearly3Price).add(yearly4Price).add(yearly5Price).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return new CostItem(role,
                yearlyPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN),
                yearly2Price.setScale(2, BigDecimal.ROUND_HALF_DOWN),
                yearly3Price.setScale(2, BigDecimal.ROUND_HALF_DOWN),
                yearly4Price.setScale(2, BigDecimal.ROUND_HALF_DOWN),
                yearly5Price.setScale(2, BigDecimal.ROUND_HALF_DOWN),
                totalPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN)
                );
    }
}
