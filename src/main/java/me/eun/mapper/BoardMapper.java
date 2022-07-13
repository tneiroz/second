package me.eun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.eun.model.Board;
import me.eun.model.BoardAttachVO;
import me.eun.model.Criteria;

public interface BoardMapper {
	List<Board> getList(Criteria criteria);
	Board get (Long bno);
	void insert (Board board);
	void update(Board board);
	void delete (Long bno);
	int totalCount(Criteria criteria);
	void updateReplyCnt(@Param("bno") Long bno,@Param ("amount") int amount);
	void addViewCount (Long bno);
	List<BoardAttachVO> attachList(Long bno);
}
