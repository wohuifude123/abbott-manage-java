package cn.spring.inter.service.impl;

import cn.spring.inter.entity.MessageBoard;
import cn.spring.inter.mapper.Mapper.MessageBoardMapper;
import cn.spring.inter.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MessageBoardService")
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public List<MessageBoard> getAllMessageBoard()  {
        return messageBoardMapper.getAllMessageBoard();
    }
}