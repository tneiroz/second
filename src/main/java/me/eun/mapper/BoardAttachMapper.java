package me.eun.mapper;

import java.util.List;

import me.eun.model.BoardAttachVO;

public interface BoardAttachMapper {
	
	void insert(BoardAttachVO vo);
	void delete(String uuid);
	List<BoardAttachVO> findByBno(Long bno);
}
