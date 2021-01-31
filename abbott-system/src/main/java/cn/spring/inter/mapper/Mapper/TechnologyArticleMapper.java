package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.TechnologyArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //指定这是一个操作数据库的mapper
public interface TechnologyArticleMapper {
    List<TechnologyArticle> selectAll(Integer start, Integer dataLength, String sort);
    TechnologyArticle selectById(Integer id);
    int updateByPrimaryKeySelective(TechnologyArticle technologyArticle);
    int getCount();
    List<TechnologyArticle> selectByKey(String searchKey, Integer start, Integer dataLength, String sort);
}
