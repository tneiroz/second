package me.eun.service;

import java.util.List;
import me.eun.model.Board;
import me.eun.model.BoardAttachVO;
import me.eun.model.Criteria;

public interface BoardService {
	List<Board> getList(Criteria criteria);
	Board get (Long bno, boolean isAddCount );
	void register (Board board);
 	void modify (Board board);
	void remove (Long bno);
	int totalCount(Criteria criteria);
	List<BoardAttachVO>getAttacList(Long bno);
	Board get(Long bno);
}
