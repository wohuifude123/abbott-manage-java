package com.abbott.inter.service;

import com.abbott.inter.entity.Aggregation;

import java.util.List;

public interface AggregationService {
    List<Aggregation> findAll(Integer start, Integer dataLength);
}


