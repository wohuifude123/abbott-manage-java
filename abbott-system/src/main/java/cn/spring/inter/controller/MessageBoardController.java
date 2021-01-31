package cn.spring.inter.controller;

import cn.spring.inter.entity.MessageBoard;
import cn.spring.inter.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/messageBoard")
public class MessageBoardController {

    @Autowired
    private MessageBoardService messageBoardService;

    @RequestMapping(value = "/messageBoard/v1/findAll", method={ RequestMethod.POST, RequestMethod.GET })
    public List<MessageBoard> findAll(){
        return messageBoardService.getAllMessageBoard();
    }

    @RequestMapping(value="/messageBoard",method=RequestMethod.GET)
    public List<MessageBoard> getAll(
            @RequestParam(value="start", required=false,defaultValue = "0") Integer start,
            @RequestParam(value="dataLength", required=false,defaultValue = "100") Integer dataLength){
//        System.out.println("start="+start+"/dataLength="+dataLength);
        return messageBoardService.getAllMessageBoard();
    }
}
