package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Student;

public class StudentDAO {

	private SqlSessionFactory factory;
	
	private static StudentDAO dao = new StudentDAO();
	private StudentDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static StudentDAO getInstance() {
		return dao;
	}
	
	private final String NS = "mybatis.mapper.student.";
	
	// 1. 목록
	public List<Student> selectAllStudentList() {
		SqlSession ss = factory.openSession();
		List<Student> students = ss.selectList(NS + "selectAllStudentList");
		ss.close();
		return students;
	}
	
	// 2. 전체 학생 수
	public int getAllStudentCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "getAllStudentCount");
		ss.close();
		return count;
	}
	
	// 3. 전체 점수 평균
	public double getAllStudentAverage() {
		SqlSession ss = factory.openSession();
		double average = ss.selectOne(NS + "getAllStudentAverage");
		ss.close();
		return average;
	}
	
	// 4. 삽입
	public int insertStudent(Student student) {
		SqlSession ss = factory.openSession(false);
		int insertResult = ss.insert(NS + "insertStudent", student);
		if(insertResult == 1) {
			ss.commit();
		}
		ss.close();
		return insertResult;
	}
	
	// 5. 학생 조회
	public List<Student> findStudentList(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		List<Student> students = ss.selectList(NS + "findStudentList", map);
		ss.close();
		return students;
	}
	
	// 6. 조회된 학생 수
	public int getFindStudentCount(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "getFindStudentCount", map);
		ss.close();
		return count;
	}
	
	// 7. 조회된 학생 점수 평균
	public double getFindStudentAverage(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		double average = ss.selectOne(NS + "getFindStudentAverage", map);
		ss.close();
		return average;
	}
	
	// 8. 삭제
	public int deleteStudent(int stuNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteStudent", stuNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
	// 9. 상세
	public Student selectStudentByNo(int stuNo) {
		SqlSession ss = factory.openSession();
		Student student = ss.selectOne(NS + "selectStudentByNo", stuNo);
		ss.close();
		return student;
	}
	
	// 10. 수정
	public int updateStudent(Student student) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateStudent", student);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 11. TOP3
	public List<Student> selectTop3() {
		SqlSession ss = factory.openSession();
		List<Student> top3 = ss.selectList(NS + "selectTop3");
		ss.close();
		return top3;
	}
	
}
