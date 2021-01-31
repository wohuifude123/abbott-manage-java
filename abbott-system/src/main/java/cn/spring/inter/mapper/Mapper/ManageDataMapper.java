package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.ManageData;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ManageDataMapper {
    List<ManageData> selectAll(Integer start, Integer dataLength, String sort);
}

