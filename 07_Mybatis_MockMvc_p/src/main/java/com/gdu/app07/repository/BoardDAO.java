package com.gdu.app07.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	// 마이바티스를 통해 board.xml을 불러올 수 있다.....
	private final String NS = "mybatis.mapper.board.";						// 메소드 호출을 하는 .임 ㅋ
	
	public List<BoardDTO> selectBoardList() {
		return sqlSessionTemplate.selectList(NS + "selectBoardList");		// select문은 반환값이 없으면(게시글이 없다면) 그대로 list로 반환
	}
	
	public BoardDTO selectBoardByNo(int boardNo) {
		return sqlSessionTemplate.selectOne(NS + "selectBoardByNo", boardNo); 	// selectOne: 셀렉문에 값이 없으면 null을 반환해준다. 
	}
	
	public int insertBoard(BoardDTO board) {
		return sqlSessionTemplate.insert(NS + "insertBoard", board); 		// 매개변수가 BoardDTO board이니까 (NS + "insertBoard", board)입력 해주기
	}
	
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update(NS + "updateBoard", board);
	}
	
	public int deleteBoard(int boardNo) {
		return sqlSessionTemplate.delete(NS + "deleteBoard", boardNo);
	}
}
