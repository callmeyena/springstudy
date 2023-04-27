package com.gdu.app07.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;

/*
 	DAO는 mapper로부터 Array list값을 받아오고 다시 service에게 주는 것이 DAO의 역할
*/

@Repository					// @Repository: 나를 Spring Container에 Bean으로 넣어달라!
public class BoardDAO {
	
	/*
	 	BoardDAO에서
		@Autowired
		private SqlSessionTemplate sqlSessionTemplate;
		했으니까 즉, 스프링 컨테이너가 board.xml(sqlsession)과 BoardDAO간의 의존 관계를 자동으로 연결해줘서
		BoardDAO에서 board.xml(sqlsession)에서 작성한 sql문을 호출할 수 있게 되는 것
	 */
	
	@Autowired				// 클래스간의 의존관계를 Spring Container가 자동으로 연결해주는 애너테이션
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NS = "mybatis.mapper.board.";
	
	public List<BoardDTO> selectBoardList() {			// board에 selectBoardList에서 DAO로 받은 값이 없기때문에 service로 전달할 값이 없다. 값을 받아왔으면 전달해야하고, 받은게 없으면 줄 것도 없음 ㅋ
		return sqlSessionTemplate.selectList(NS + "selectBoardList"); 	// Mybatis의 이름을 적을 때는 namespace를 적어준다 + 해당 메소드(?)의 id ==> mybatis.mapper.board.selectBoardList
		// service에게 값을 반환하기 위해선 뭐가 필요하다? => return
	}
	
	public BoardDTO selectBoardByNo(int boardNo) {		// board.xml 참조하기 => parameterType="int" int값이 있어야함 따라서 서비스로부터 값을 받아와야하고 값을 전달해줘야한다.
		return sqlSessionTemplate.selectOne(NS + "selectBoardByNo", boardNo); // service에서 값을 받아온다 => 데이터만 DAO가 받는 것 그리고 그걸 그대로 board.xml에 전달 => board.xml 메소드에 매개변수 값 받아서 실행 => 그리고 그걸 다시 실행된걸 DAO에서 메소드 실행
		
	/*
		 	sqlSessionTemplate.selectOne(NS + "selectBoardByNo", boardNo); 이 코드가
		 	board.xml의 	
		<select id="selectBoardByNo" parameterType="int" resultType="Board">
			SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT 
			  FROM BOARD 
			 WHERE BOARD_NO = #{boardNo}
		</select>
			인 것.. 뭔소리야?
	*/
		
	}
	
	public int insertBoard(BoardDTO board) {							// 메소드 이름은 board.xml select문의 id
		return sqlSessionTemplate.insert(NS + "insertBoard", board);	// 서비스한테 받은 BoardDTO의 board값을 mapper board.xml에 그대로 전달	=> 메소드 호출 결과 그대로 반환한다
	}
	
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update(NS + "updateBoard", board);
	}
	
	public int deleteBoard(int boardNo) {
		return sqlSessionTemplate.delete(NS + "deleteBoard", boardNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
