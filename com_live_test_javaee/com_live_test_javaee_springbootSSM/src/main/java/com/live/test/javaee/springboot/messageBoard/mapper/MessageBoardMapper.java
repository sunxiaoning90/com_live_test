package com.live.test.javaee.springboot.messageBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.live.test.javaee.springboot.messageBoard.po.MessageBoard;

/**
 * DAO
 * @author live
 */
@Mapper
public interface MessageBoardMapper {
	
	MessageBoard getById(Integer id);
	
	List<MessageBoard> getAll();
	
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into im_messageboard(id, content, time) values (#{id},#{content},#{time})")
    int save(MessageBoard record);
    
}