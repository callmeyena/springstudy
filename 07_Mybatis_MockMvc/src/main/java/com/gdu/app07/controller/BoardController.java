package com.gdu.app07.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app07.service.BoardService;
import com.gdu.app07.service.BoardServiceImpl;

@Controller
@RequestMapping("/board")	// /board로 시작하는 모든 mapping들은 모두 BoardController가 작성하겠다.
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 
		Controller가 가지고 있는 @RequestMapping 애너테이션을 사용하여 /list.do만 표기 가능 
		하지만 결과적으론 @RequestMapping(/board) + @GetMapping(/list.do) = /board/list.do 라는 경로임
		
		데이터(속성) 저장 방법
		1. forward  : Model에 Attribute로 저장한다.
		2. redirect : 원래는 저장이 안되지만 Spring에서는 redirect할 때 속성 저장하라고 RedirectAttributes 에 flashAttribute로 저장한다.
		
	 */
	// // getBoardList() 서비스가 반환한 List<BoardDTO>를 /WEB-INF/views/board/list.jsp로 전달한다.
	@GetMapping("/list.do") 	// 위에 주석들 참고
	public String list(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	// getBoardByNo() 서비스가 반환한  BoardDTO를 /WEB-INF/views/board/list.jsp로 전달한다.
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(request));
		return "board/detail";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
									 
	// addBoardService() 서비스가 반환한 0 또는 1을 가지고 /board/list.do으로 이동(redirect)한다.
	// addBoardService() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/list.jsp에서 확인한다.
	@PostMapping("/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {	// Model model대신 RedirectAttributes redirectAttributes 선언 된 것 => redirect이기 때문
		redirectAttributes.addFlashAttribute("addResult", boardService.addBoard(request));	// addAttribute와 혼동하면 안됨! addFlashAttribute해야 원하는 list.do를 지나 board/list까지 전달해줌
		return "redirect:/board/list.do";
	}
	
	// modifyBoard() 서비스가 반환한 0 또는 1을 가지고 /board/detail.do으로 이동(redirect)한다.
	// modifyBoard() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/detail.jsp에서 확인한다.
	@PostMapping("/modify.do")
	public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modifyResult", boardService.modifyBoard(request));
		return "redirect:/board/detail.do?boardNo=" + request.getParameter("boardNo");
	}
	
	// removeBoardService() 서비스가 반환한 0 또는 1을 가지고 /board/list.do으로 이동(redirect)한다.
	// removeBoardService() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/list.jsp에서 확인한다.
	@PostMapping("/remove.do")
	public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", boardService.removeBoard(request));
		return "redirect:/board/list.do";
	}
	
	// 트랜잭션 테스트
	@GetMapping("/tx.do")
	public void tx() {
		boardService.testTx();
	}
	

}
