package cn.spring.inter.service;

import cn.spring.inter.entity.ManageData;
import java.util.List;

public interface ManageDataService {
    List<ManageData> selectAll(Integer start, Integer dataLength, String sort);
}

