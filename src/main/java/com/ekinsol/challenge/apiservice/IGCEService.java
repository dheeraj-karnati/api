package com.ekinsol.challenge.apiservice;

import com.ekinsol.challenge.apiservice.bo.CostItem;

import java.util.List;

public interface IGCEService {
    List<CostItem> getCostEstimation();
}
