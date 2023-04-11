package com.gdu.app01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
	1.객체(클래스 생성) -> 2.bean 생성 -> 3.AbstractApplicationContext 으로 bean 가져오기
		 - 스프링은 객체를 생성하고 각각의 객체를 연결해주는 조립기 역할을 한다.
		 - 여기에 있는 GenericXmlApplicationContext 객체가 조립기 기능을 구현한 클래스이다.
		 - 조립기에서 생성할 객체가 무엇이고, 각 객체를 어떻게 연결하는 지에 대한 정보는 xml 파일에 설정이 되어 있다.
		 - GenericXmlApplicationContext 클래스는 xml 파일에 정의된 설정 정보를 읽어와서 객체를 생성하고,
		   각각의 객체를 연결한 뒤에 내부적으로 보관한다.
		 - xml을 이용한 스프링 설정 파일에서는 컨테이너가 생성할 객체를 지정하기 위해 <bean> 태그를 사용한다.
		 - 스프링 컨테이너가 생성해서 보관하는 객체를 스트링 빈(bean) 객체라고 부르며, 일반적으로 자바 객체와 동일하다.
		 - 스프링 컨테이너는 생성한 빈 객체를 <이름, 빈 객체> 쌍으로 보관환다.
		 - 스프링 컨테이너가 보관하고 있는 객체를 사용하고 싶은 경우 빈 객체와 연결되어 있는 이름을 이용해서 객체 참조한다.
*/
/*
	 * 스프링 컨테이너의 종류 
		  BeanFactory : 단순히 스프링 컨테이너에서 객체를 생성하고 DI만 처리해주는 기능을 제공하는 객체이다.
		   스프링을 사용하는 이유는 단순히 DI사용을 위해서가 아니라, 스프링을 사용하는 다양한 부가기능
		   (트랜잭션 처리, 자바 코드 기반의 스프링 설정, 애노테이션을 이용한 빈 설정, 스프링을 이용한 웹 개발 등)
		   때문인데 이러한 부가적인 기능을 사용하기 위해서는 ApplicationContext객체를 주로 사용한다.
		 - AbstractApplicationContext : 컨테이너 종료(close)와 같은 기능을 제공해 주는 객체이다.
		 - GenericXmlApplicationContext : AbstractApplicationContext 객체를 상속 받아 만드는 클래스. xml 파일에서 스프링 빈 설정 정보를 읽어오는 역할을 수행한다.
		 - GenericXmlApplicationContext 객체를 생성할 때 파라미터 값으로 xml의 경로를 전달하여 설정 파일로 사용한다.
		 - GenericXmlApplicationContext 객체는 전달받은 xml 파일에서 설정 정보를 읽어와서 스프링 빈 객체를 생성하고 프로퍼티 값을 설정한다.
		   생성된 스프링 빈 객체는 getBean() 이라는 메서드를 사용해서 구현한다.
		   getBean() 메서드의 첫번쨰 파라미터는 구현하고자 하는 스프링 빈 객체의 고유한 id이름이며, 두번째 파라미터는 그 객체의 타입을 의미한다.
*/

public class MainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml03/app-context.xml");
		
		Board board = ctx.getBean("board", Board.class);
		System.out.println("제목: " + board.getTitle());
		System.out.println("내용: " + board.getContent());
		System.out.println("작성자ID: " + board.getWriter().getId());
		System.out.println("작성자명: " + board.getWriter().getName());
		
		ctx.close();

	}

}