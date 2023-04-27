package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app07.domain.BoardDTO;

/*
 	컨트롤러에서 서비스 값 받아옴 그리고 DAO로 값 전달 그다음 다시 컨트롤러로 보내는거? 도대체 뭔말?
*/
public interface BoardService {
	public List<BoardDTO> getBoardList();		//  controller에서 값 받아올 거 없음 왜냐? DAO에서도 받을 매개변수 값이 없으니까 service에서도 값을 받을 필요 없고 DAO에게도 줄 게 없다
												// 목록보기 메소드는 DAO로 부터 Array List를 받아옴	
	public BoardDTO getBoardByNo(HttpServletRequest request);	// 밑줄 참고		
	/*  jsp에서 controller에게 자료를 넘겨줌 => controller가 service에 자료를 넘겨줌 => service에서 DAO로 자료를 넘겨줌 => DAO에서 board.xml로 자료를 넘겨줌
	jsp에서 controller로 값을 전달하는 방법 3가지 1) HttpServletRequest 2) @애너테이션 3) 객체
	
	 */
	
	public int addBoard(HttpServletRequest request);			// 냅다 매개변수 값 다 받아오고 결과처리는 controller로 받아오겠다고 함 ㅋㅋ 먼소리지/ 밑줄참고
	// 컨트롤러에서 서비스로 값을 줄 때 http~~ request를 받고 그 파라미터 값을 dto로 바꿔주고 dao한테 준다...?
	
	public int modifyBoard(HttpServletRequest request);
	public int removeBoard(HttpServletRequest request);		// jsp에서 controller에게 Http~~ request로 값을 주겠다 그걸 service 가 그대로 받겠다 	
	public void testTx();
}
