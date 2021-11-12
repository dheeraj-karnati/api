package com.ekinsol.challenge.apiservice.controller;

import com.ekinsol.challenge.apiservice.bo.CostItem;
import com.ekinsol.challenge.apiservice.service.IGCEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IGCEController {

    @Autowired
    private IGCEService igceService;

    public IGCEController() {
    }

    @GetMapping("/costitems")
    public List<CostItem> getCostItems() {
        return igceService.getCostEstimation();
    }
}
