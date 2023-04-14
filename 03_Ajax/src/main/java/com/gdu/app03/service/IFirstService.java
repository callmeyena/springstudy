package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.Person;

public interface IFirstService {
	public Person execute1(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> execute2(String name, int age);
	
	
/*	jsp서블릿은 과도하게 많이 만듦, 클래스 하나당 메소드 하나가지고 기능 하나 한것. 
	그러다보니 기능이 다섯개면 클래스 다섯개 클래스당 메소드 하나 생성됨. 원래 메소드는 하나의 클래스에 여러개의 메소드를 만들수 있음. 
	그와 같이 만들겠다는 것( 하나의 인터페이스에 여러개의 메소드를 배치해서 하나의 클래스에서 모든 기능을 구현하는 방식 -> 수업 끝날때까지 서비스 구현할 방식) 
*/

}
