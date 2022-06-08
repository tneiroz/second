package me.eun.mapper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.eun.config.RootConfig;
import me.eun.model.Board;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class BoardMapperTest extends AppTest {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void setUp() throws IOException, SQLException {
		Reader reader = Resources.getResourceAsReader("sql/sql_board2.sql");
		ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
		runner.runScript(reader);
		
	}
	
	@Test
	public void getListTest() {
		List<Board> list = boardMapper.getList();
		assertEquals(4, list.size());
		
	}
	@Test
    public void getTest() {
		Board board = boardMapper.get(1L);
		assertEquals("게시물 제목입니다1", board.getTitle());
		assertEquals("게시물내용1",board.getContent());
		assertEquals("작성자1",board.getWriter());
	}
	
	@Test
	public void insertTest() {
		Board board = new Board();
		board.setTitle("제목 테스트");
		board.setContent("내용 테스트");
		board.setWriter("작성자테스트");
		boardMapper.insert(board);
		Board getBoard = boardMapper.get(5L);
		
		assertEquals(board.getTitle(),getBoard.getTitle());
		assertEquals(board.getContent(),getBoard.getContent());
		assertEquals(board.getWriter(), getBoard.getWriter());
		
	}
	
	
}
