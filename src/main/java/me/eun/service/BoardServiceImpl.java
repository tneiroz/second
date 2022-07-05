package me.eun.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.eun.mapper.BoardAttachMapper;
import me.eun.mapper.BoardMapper;
import me.eun.model.Board;
import me.eun.model.BoardAttachVO;
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
		boardMapper.insert(board);
		if(board.getAttachList()==null || board.getAttachList().size()==0) return;
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}
	@Transactional
	@Override
	public void modify(Board board) {
		System.out.println(board.getAttachList());
		attachMapper.deleteAll(board.getBno());
		boardMapper.update(board);
		if(board.getAttachList()!=null) {
			
			board.getAttachList().forEach(attach->{
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			
			});
		}
	}
	@Transactional
	@Override
	public void remove(Long bno) {
		attachMapper.deleteAll(bno);
		boardMapper.delete(bno);

	}

	@Override
	public int totalCount(Criteria criteria) {
		return boardMapper.totalCount(criteria);
	}

	@Override
	public List<BoardAttachVO> getAttacList(Long bno) {
		return attachMapper.findByBno(bno);
	}

}
