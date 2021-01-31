package cn.spring.inter.service.impl;

import cn.spring.inter.entity.ManageData;
import cn.spring.inter.mapper.Mapper.ManageDataMapper;
import cn.spring.inter.service.ManageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ManageDataService")
public class ManageDataServiceImpl implements ManageDataService {
    @Autowired
    private ManageDataMapper manageDataMapper;

    @Override
    public List<ManageData> selectAll(Integer start, Integer dataLength, String sort) {
        return manageDataMapper.selectAll(start, dataLength, sort);
    }
}