package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

@Service		// @Service라고 애너테이션을 달아줘야 Spring Container에 Bean저장
public class BoardServiceImpl implements BoardService {		// DAO를 필드로 저장한다
	/*
	 	@Repository
	 	@Service
	 	등으로 애너테이션 해줌으로써 spring container에 bean으로 저장이 되고 그 값을 받아 오기 위해 쓰는 애너테이션이 @autowired??
	 */
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardList() {	// 받아온 값이 없으니 가공하고 말것도 없이 return해준다 => boardDAO를 불러서 그 값을 controller에게 전달하는 역할
		return boardDAO.selectBoardList();	// 이 메소드를 return함으로써 Array List가 실행된다?
	}

	@Override
	public BoardDTO getBoardByNo(HttpServletRequest request) {		// ex) jsp에서 상세보기 하고 싶은거 => detail.do?boardNo=5 => controller는 http~~request로 받기로함 => boardNo 값만 빼서 받음 
		// 파라미터 boardNo가 없으면(null, "") 0을 사용한다.		// => 그 boardNo를 DAO에게 전달 => 나중에 다시 DAO에서 서비스로 값 받음(boardNo) => 그 값을 그대로 controller에 전달
		String strBoardNo = request.getParameter("boardNo");		// parameter값은 무조건 String으로만 받아야 한다.
		int boardNo = 0;
		if(strBoardNo != null && strBoardNo.isEmpty() == false) {
			boardNo = Integer.parseInt(strBoardNo);					// 0이거나 0이 아닌 값을 int로 변환해서 DAO에게 줘라
		}
		return boardDAO.selectBoardByNo(boardNo);					// 항상 DAO의 결과는 return에 있을 것이다.
	}

	@Override
	public int addBoard(HttpServletRequest request) {
		try {
			// 파라미터 title, content, writer 값을 받아온다. 숫자로 받아올 파라미터 값 없음
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			// BoardDAO로 전달할 BoardDTO를 만든다.
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContent(content);
			board.setWriter(writer);
			return boardDAO.insertBoard(board);	// DAO로 전달
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int modifyBoard(HttpServletRequest request) {
		try {
			
			// 파라미터 title, content, boardNo를 받아온다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			// BoardDAO로 전달할 BoardDTO를 만든다.
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContent(content);
			board.setBoardNo(boardNo);
			return boardDAO.updateBoard(board);	// DAO로 전달
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int removeBoard(HttpServletRequest request) {
		// 파라미터 boardNo를 받아온다.
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		return boardDAO.deleteBoard(boardNo);
	}
	
	// 트랜잭션 확인
	@Transactional		// insert, update, delete를 두 개 이상 사용할때 쓰는 애너테이션
	@Override
		public void testTx() {
			boardDAO.insertBoard(new BoardDTO(0, "제목", "내용", "작성자", null, null));	// 성공
			boardDAO.insertBoard(new BoardDTO());	// 실패
		}

}
