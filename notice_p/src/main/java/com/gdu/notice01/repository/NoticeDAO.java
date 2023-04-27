package com.gdu.notice01.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.notice01.domain.NoticeDTO;

@Repository
public class NoticeDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");		// 드라이버 로드
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");		// 실제 연결 완
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<NoticeDTO> getNoticeList() {
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		try {
			con = getConnection();
			sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE ORDER BY NOTICE_NO DESC"; 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				NoticeDTO notice = new NoticeDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				list.add(notice);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public NoticeDTO getNotice(int notice_no) {
		NoticeDTO notice = null;
		try {
			con = getConnection();
			sql = "SELECT NOTICE_NO, GUBUN, TITLE, CONTENT FROM NOTICE WHERE NOTICE_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				notice = new NoticeDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return notice;
	}
	
	public int addNotice(NoticeDTO notice) {
		int addResult = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice.getGubun());
			ps.setString(2, notice.getTitle());
			ps.setString(3, notice.getContent());
			addResult = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return addResult;
	}
	
	public int modifyNotice(NoticeDTO notice) {
		int modifyResult = 0;
		try {
			con = getConnection();
			sql ="UPDATE NOTICE SET GUBUN = ?, TITLE = ?, CONTENT = ? WHERE NOTICE_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, notice.getGubun());
			ps.setString(2, notice.getTitle());
			ps.setString(3, notice.getContent());
			ps.setInt(4, notice.getNotice_no());
			modifyResult = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return modifyResult;
	}
	
	public int removeNotice(int notice_no) {
		int removeResult = 0;
		try {
			con = getConnection();								// DB접속
			sql = "DELETE FROM NOTICE WHERE NOTICE_NO = ?";		// 쿼리문 적기
			ps = con.prepareStatement(sql);						// 쿼리문 실행할 준비
			ps.setInt(1, notice_no);							// 쿼리문을 실행하기 위해서 필요한 데이터 값 삽입
			removeResult = ps.executeUpdate();					// 실행 결과를 removeResult에 저장
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return removeResult;
	}
	

}
