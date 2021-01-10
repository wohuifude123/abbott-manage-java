package cn.spring.inter.service;

import cn.spring.inter.entity.MessageBoard;
import java.util.List;

public interface MessageBoardService {
    List<MessageBoard> getAllMessageBoard();
}