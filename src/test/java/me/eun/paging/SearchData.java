package me.eun.paging;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.eun.AppTest;
import me.eun.mapper.BoardMapper;
import me.eun.model.Board;

public class SearchData extends AppTest {
	
	@Autowired
	BoardMapper mapper;
	
	@Test
	@Ignore
	public void dataInsert() {
		for(int i =1; i<=212; i++) {
			Board board = new Board();
			board.setTitle("검색 처리 연습 : Spring " +i );
			board.setContent("내용 처리 연습 : Spring Boot " +i );
			board.setWriter("짱구");
			mapper.insert(board);
		}
	}
		
		@Test
		public void dataInsert2() {
			for(int i =1; i<=212; i++) {
				Board board = new Board();
				board.setTitle("검색 처리 연습 : java " +i );
				board.setContent("내용 처리 연습 : jsp " +i );
				board.setWriter("맹구");
				mapper.insert(board);
			}
		}
}
