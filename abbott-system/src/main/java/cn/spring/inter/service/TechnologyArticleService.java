package cn.spring.inter.service;

import cn.spring.inter.entity.TechnologyArticle;
import java.util.List;

public interface TechnologyArticleService {
    List<TechnologyArticle> selectAll(Integer start, Integer dataLength, String sort);
    TechnologyArticle selectById(Integer id);
    int updateByPrimaryKeySelective(TechnologyArticle technologyArticle);
    int getCount();
    List<TechnologyArticle> selectByKey(String searchKey, Integer start, Integer dataLength, String sort);
}

