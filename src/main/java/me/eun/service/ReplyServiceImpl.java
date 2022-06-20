package me.eun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import me.eun.mapper.ReplyMapper;
import me.eun.model.Criteria;
import me.eun.model.ReplyVO;

@Service

public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> getList(Criteria criteria, Long bno) {
		return mapper.getListWithPaging(criteria, bno);
	}

	@Override
	public int register(ReplyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	public ReplyMapper getMapper() {
		return mapper;
	}

	
}
