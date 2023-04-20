package com.gdu.notice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.notice.domain.NoticeDTO;

@Repository
public class NoticeDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// GDJ61 계정에 접속할 수 있는 Connection 객체를 반환하시오. 예외 처리(try - catch) 없이 진행하시오.
	private Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
	}
	
	// DB 처리 시 사용한 Connection, PreparedStatement, Result 객체를 close 처리하시오.
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 모든 공지사항을 NOTICE_NO의 내림차순으로 정렬하여 반환하시오.
	public List<NoticeDTO> getNoticeList() {
		return null;
	}
	
	// 전달 받은 공지번호(notice_no)를 가진 공지사항을 반환하시오. 공지번호가 일치하는 공지사항이 없다면 null을 반환하시오.
	public NoticeDTO getNotice(int notice_no) {
		return null;
	}
	
	// 전달된 NoticeDTO 객체의 값을 이용해서 NOTICE 테이블에 데이터를 삽입하시오. 삽입 결과를 반환하시오.
	public int addNotice(NoticeDTO notice) {
		return 0;
	}
	
	// 전달된 NoticeDTO 객체의 값을 이용해서 NOTICE 테이블에 데이터를 수정하시오. 수정 결과를 반환하시오.
	public int modifyNotice(NoticeDTO notice) {
		return 0;
	}

	// 전달 받은 공지번호(notice_no)를 가진 공지사항을 삭제하시오. 삭제 결과를 반환하시오.
	public int removeNotice(int notice_no) {
		return 0;
	}
	
}
