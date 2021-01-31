package com.abbott.inter.service.impl;

import com.abbott.inter.entity.Aggregation;
import com.abbott.inter.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("AggregationService")
public class AggregationServiceImpl implements AggregationService {

    @Override
    public List<Aggregation> findAll (Integer start, Integer dataLength) {
        Aggregation agg = new Aggregation();
        List<Aggregation> aggList = new ArrayList();
        aggList.add(agg);
        return aggList;
    }


}


