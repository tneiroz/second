package me.eun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.eun.model.Criteria;
import me.eun.model.ReplyVO;

public interface ReplyMapper {
	List<ReplyVO> getListAll();
	List<ReplyVO> getListWithPaging(@Param("cri") Criteria criteria,@Param("bno") Long bno);
	
	
	int insert(ReplyVO vo);
	// int insert(@param이 생략된 형태)
	ReplyVO read(Long rno);
	int delete(Long rno);
	int update(ReplyVO vo);
	
}
