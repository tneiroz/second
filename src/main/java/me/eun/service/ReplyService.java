package me.eun.service;

import java.util.List;

import me.eun.model.Criteria;
import me.eun.model.ReplyVO;

public interface ReplyService {
		List<ReplyVO> getList(Criteria criteria , Long bno);
		int register(ReplyVO vo);
		ReplyVO get(Long rno);
		int remove(Long rno);
		int modify(ReplyVO vo);
	}

