package com.gdu.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	/*
		데이터(속성, Attribute) 전달 방법
		1. forward인 경우 Model에 attribute로 저장한다.
		2. redirect인 경우 RedirectAttributes에 flash attribute로 저장한다.
	*/
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/notice/list.do")
	public String list(Model model) {
		// notice/list.jsp로 forward할 때 목록 조회 결과를 보내기 위해서 Model을 사용한다.
		model.addAttribute("noticeList", noticeService.getNoticeList());
		return "notice/list";
	}
	
	@GetMapping("/notice/write.do")
	public String write() {
		return "notice/write";
	}
	
	@PostMapping("/notice/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// /notice/list.do로 redirect할 때 삽입 결과(0 또는 1)를 보내기 위해서 RedirectAttributes를 사용한다. 삽입 결과에 따른 경고창 출력 코드는 list.jsp에 있다.
		redirectAttributes.addFlashAttribute("addResult", noticeService.addNotice(request));
		return "redirect:/notice/list.do";
	}
	
	@GetMapping("/notice/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		// notice/detail.jsp로 forward할 때 상세 조회 결과를 보내기 위해서 Model을 사용한다.
		model.addAttribute("notice", noticeService.getNotice(request));
		return "notice/detail";
	}
	
	@PostMapping("/notice/modify.do")
	public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// /notice/detail.do로 redirect할 때 수정 결과(0 또는 1)를 보내기 위해서 RedirectAttributes를 사용한다. 수정 결과에 따른 경고창 출력 코드는 detail.jsp에 있다.
		redirectAttributes.addFlashAttribute("modifyResult", noticeService.modifyNotice(request));
		return "redirect:/notice/detail.do?notice_no=" + request.getParameter("notice_no");
	}
	
	@GetMapping("/notice/remove.do")
	public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// /notice/list.do로 redirect할 때 삭제 결과(0 또는 1)를 보내기 위해서 RedirectAttributes를 사용한다. 삭제 결과에 따른 경고창 출력 코드는 list.jsp에 있다.
		redirectAttributes.addFlashAttribute("removeResult", noticeService.removeNotice(request));
		return "redirect:/notice/list.do";
	}
	
}
