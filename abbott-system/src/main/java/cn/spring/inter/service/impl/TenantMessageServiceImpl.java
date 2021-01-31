package cn.spring.inter.service.impl;

import cn.spring.inter.entity.TenantMessage;
import cn.spring.inter.mapper.Mapper.TenantMessageMapper;
import cn.spring.inter.service.TenantMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TenantMessageService")
public class TenantMessageServiceImpl implements TenantMessageService {
    @Autowired
    private TenantMessageMapper tenantMessageMapper;

    @Override
    public List<TenantMessage> getAll(Integer start, Integer dataLength) {
        return tenantMessageMapper.getAll(start, dataLength);
    }

    @Override
    public Integer getCount() {
        return tenantMessageMapper.getCount();
    }
}


