package cn.spring.inter.controller;

import cn.spring.inter.entity.MessageBoard;
import cn.spring.inter.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messageBoard")
public class MessageBoardController {

    @Autowired
    private MessageBoardService messageBoardService;

    @RequestMapping(value = "/v1/findAll", method={ RequestMethod.POST, RequestMethod.GET })
    public List<MessageBoard> findAll(){
        return messageBoardService.getAllMessageBoard();
    }
}
