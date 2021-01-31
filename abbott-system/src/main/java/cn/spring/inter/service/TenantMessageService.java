package cn.spring.inter.service;

import cn.spring.inter.entity.TenantMessage;

import java.util.List;

public interface TenantMessageService {
    List<TenantMessage> getAll(Integer start, Integer dataLength);
    Integer getCount();
}