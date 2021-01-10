package cn.spring.inter.mapper.Mapper;

import cn.spring.inter.entity.MessageBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageBoardMapper {
    @Select("select mid, name, content, pubtime, wechart from message_board")
    List<MessageBoard> getAllMessageBoard();
}
