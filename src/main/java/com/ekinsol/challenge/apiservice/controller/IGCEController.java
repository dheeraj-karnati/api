package com.ekinsol.challenge.apiservice.controller;

import com.ekinsol.challenge.apiservice.IGCEService;
import com.ekinsol.challenge.apiservice.bo.CostItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IGCEController {

    private final IGCEService igceService;

    public IGCEController(IGCEService igceService) {
        this.igceService = igceService;
    }

    @GetMapping("/costitems")
    public List<CostItem> getCostItems() {
        return igceService.getCostEstimation();
    }
}
