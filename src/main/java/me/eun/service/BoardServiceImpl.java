package me.eun.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.eun.mapper.BoardAttachMapper;
import me.eun.mapper.BoardMapper;
import me.eun.model.Board;
import me.eun.model.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
	@Override
	public List<Board> getList(Criteria criteria) {
		return boardMapper.getList(criteria);
	}

	@Override
	public Board get(Long bno) {
		return boardMapper.get(bno);
		
	}
	@Transactional
	@Override
	public void register(Board board) {
		//boardMapper.insert(board);
		System.out.println(board.getAttachList());
		if(board.getAttachList()==null || board.getAttachList().size()==0) return;
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public void modify(Board board) {
		boardMapper.update(board);

	}

	@Override
	public void remove(Long bno) {
		boardMapper.delete(bno);

	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardMapper.totalCount(criteria);
	}

}
