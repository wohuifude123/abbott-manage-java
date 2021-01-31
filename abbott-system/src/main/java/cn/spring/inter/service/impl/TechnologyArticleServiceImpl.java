package cn.spring.inter.service.impl;

import cn.spring.inter.entity.TechnologyArticle;
import cn.spring.inter.mapper.Mapper.TechnologyArticleMapper;
import cn.spring.inter.service.TechnologyArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TechnologyArticleService")
public class TechnologyArticleServiceImpl implements TechnologyArticleService {
    @Autowired
    private TechnologyArticleMapper technologyArticleMapper;

    @Override
    public List<TechnologyArticle> selectAll(Integer start, Integer dataLength, String sort) {
        return technologyArticleMapper.selectAll(start, dataLength, sort);
    }

    @Override
    public TechnologyArticle selectById(Integer id) {
        return technologyArticleMapper.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TechnologyArticle technologyArticle)  {
        return technologyArticleMapper.updateByPrimaryKeySelective(technologyArticle);
    }

    @Override
    public int getCount() {
        return technologyArticleMapper.getCount();
    }

    @Override
    public List<TechnologyArticle> selectByKey(String searchKey, Integer start, Integer dataLength, String sort) {
        return technologyArticleMapper.selectByKey(searchKey, start, dataLength, sort);
    }
}
