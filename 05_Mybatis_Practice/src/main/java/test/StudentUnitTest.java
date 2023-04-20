package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Student;
import repository.StudentDAO;

public class StudentUnitTest {

	//@Test
	public void 삽입테스트() {
		Student s = Student.builder()
				.name("홍길동")
				.kor(50)
				.eng(51)
				.math(53)
				.build();
		assertEquals(1, StudentDAO.getInstance().insertStudent(s));
	}
	
	//@Test
	public void 목록테스트() {
		assertEquals(1, StudentDAO.getInstance().selectAllStudentList().size());
	}
	
	//@Test
	public void 수정테스트() {
		Student s = Student.builder()
				.stuNo(1)
				.name("홍길동2")
				.kor(60)
				.eng(61)
				.math(63)
				.build();
		assertEquals(1, StudentDAO.getInstance().updateStudent(s));
	}
	
	//@Test
	public void 상세테스트() {
		Student s = StudentDAO.getInstance().selectStudentByNo(1);
		assertEquals("홍길동2", s.getName());
		assertEquals("D", s.getGrade());
	}
	
	@Test
	public void 삭제테스트() {
		assertEquals(1, StudentDAO.getInstance().deleteStudent(1));
	}

}
